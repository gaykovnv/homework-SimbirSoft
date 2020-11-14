var personApi = Vue.resource("/persons{/id}/list")

function getIndex(list , id){
    for (var i = 0; i<list.length;i++){
        if(list[i].id === id){
            return i;
        }
    }
    return -1;
}

Vue.component('person-form', {
    props: ['persons', 'personAttr'],
    data: function () {
        return {
            fname: '',
            lname: '',
            patronymic: '',
            birthday: '',
            id: ''
        }
    },
    watch: {
        personAttr: function (newValue, oldValue) {
            this.fname = newValue.fname;
            this.lname = newValue.lname;
            this.patronymic = newValue.patronymic;
            this.birthday = newValue.birthday;
            this.id = newValue.id;
        }
    },
    template: '<div align="center">' +
        '<input type="text" required placeholder="enter fname" v-model="fname"/><br />' +
        '<input type="text" required placeholder="enter lname" v-model="lname"/><br />' +
        '<input type="text" required placeholder="enter patronymic" v-model="patronymic"/><br />' +
        '<input type="date" required placeholder="enter birthday" asp-for="MyDate" asp-format="{0:dd-MM-yyyy}" v-model="birthday"/><br />' +
        '<input type="button" value="Save" @click="save">' +
        '</div>',
    methods: {
        save: function () {
            var newPerson =
                {fname: this.fname, lname: this.lname, patronymic: this.patronymic, birthday: this.birthday};
            if (this.id) {
                personApi.update({id: this.id},person).then(result =>
                    result.json().then(data =>{
                        var index = getIndex(this.persons,data.id)
                        this.persons.splice(index,1,data);
                        this.id = '';
                        this.fname = '';
                        this.lname = '';
                        this.patronymic = '';
                        this.birthday = '';
                        })
                )
            } else {
                personApi.save({}, newPerson).then(result =>
                    result.json().then(data => {
                        this.persons.push(data);
                        this.fname = ' ';
                        this.lname = ' ';
                        this.patronymic = ' ';
                        this.birthday = ' ';
                    }))
            }
        }
    }

});

Vue.component('person-row', {
    props: ['person', 'editPerson','persons'],
    template: '<div>' +
        '<i>({{person.id}})</i>' +
        '{{person.fname}} {{person.lname}} {{person.patronymic}} {{person.birthday}}' +
        '<span style="position: absolute; right: 0">' +
            '<input type="button" value="Edit" @click="edit"/>' +
            '<input type="button" value="delete" @click="del"/>' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editPerson(this.person);
        },
        del: function () {
          personApi.remove({id: this.person.id}).then(result => {
              if (result.ok) {
                  this.person.splice(this.persons.indexOf(this.person), 1)
              }
          })
        }
    }

});

Vue.component('persons-list', {
    props: ['persons'],
    data: function () {
        return {
            person: null
        }
    },
    template: '<div style="position: relative; width: 500px">' +
        '<person-form :persons="persons" :personAttr="person"/>' +
        '<person-row v-for="person in persons" :key="person.id" :person="person" ' +
            ':editPerson="editPerson" :persons="person"/>' +
        '</div>',
    created: function () {
        personApi.get().then(result =>
            result.json().then(data =>
                data.forEach(person => this.persons.push(person))
            ))
    },
    methods: {
        editPerson: function (person) {
            this.person = person;
        }
    }
});

var app = new Vue({
    el: '#app',
    template: '<persons-list :persons="persons"/>',
    data: {
        persons: []
    }
});