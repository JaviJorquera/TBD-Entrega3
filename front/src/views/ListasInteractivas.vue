<template>
    <b-container fluid>
        <b-row>
            <b-col style="border-style: groove;">
                <b-table :items="items" :fields="fields" :select-mode="selectMode" responsive="sm" ref="selectableTable" selectable @row-selected="onRowSelected" >
                    <template #cell(selected)="{ rowSelected }">
                        <template v-if="rowSelected">
                            <span aria-hidden="true">&check;</span>
                            <span class="sr-only">Selected</span>
                        </template>
                        <template v-else>
                            <span aria-hidden="true">&nbsp;</span>
                            <span class="sr-only">Not selected</span>
                        </template>
                    </template>
                </b-table>
                <p>{{selected[0].nombreEmergencia}}</p>
            </b-col >
                
            <b-col style="border-style: groove;">
               <b-table :items="items2" :fields="fields2" :select-mode="selectMode" responsive="sm" ref="selectableTable2" selectable @row-selected="onRowSelected2" >
                    <template #cell(selected)="{ rowSelected }">
                        <template v-if="rowSelected">
                            <span aria-hidden="true">&check;</span>
                            <span class="sr-only">Selected</span>
                        </template>
                        <template v-else>
                            <span aria-hidden="true">&nbsp;</span>
                            <span class="sr-only">Not selected</span>
                        </template>
                    </template>
                </b-table>
                <p>{{selected2[0].nombreTarea}}</p>
            </b-col>

            <b-col style="border-style: groove;">
                <b-table :items="items3" :fields="fields3" :select-mode="selectMode" responsive="sm" ref="selectableTable3" selectable @row-selected="onRowSelected3" >
                    <template #cell(selected)="{ rowSelected }">
                        <template v-if="rowSelected">
                            <span aria-hidden="true">&check;</span>
                            <span class="sr-only">Selected</span>
                        </template>
                        <template v-else>
                            <span aria-hidden="true">&nbsp;</span>
                            <span class="sr-only">Not selected</span>
                        </template>
                    </template>
                </b-table>
                <p>{{selected3[0].nombreVoluntario}}</p>
            </b-col>
        </b-row>
    </b-container >
</template>

<script>
    import axios from "axios";
    export default {     
        data() {
        return {
                fields: ['id_Emergencia', 'nombreEmergencia', 'id_Estado'],
                fields2: ['id_Tarea', 'nombreTarea', 'vol_requeridos', 'id_Estado'],
                fields3: ['id_Voluntario', 'nombreVoluntario', 'flg_participa'],
                items: [],
                items2: [],
                items3: [],
            selectMode: 'single',
        selected: [{}],
        selected2: [{}],
        selected3: [{}]
        }
    },
    created() {
        let apiURL = 'http://localhost:8080/emergencias/getAll';
        axios.get(apiURL).then(res => {
            this.items = res.data;
                              
        }).catch(error => {
            console.log(error)
        });
        
        
    },

    methods: {
        onRowSelected(items) {
            this.selected = items
            let apiURL2 = 'http://localhost:8080/tareas/getById_Emergencia/' + this.selected[0].id_Emergencia;
            axios.get(apiURL2).then(res => {
                this.items2 = res.data;                      
            }).catch(error => {
                console.log(error)
            }); 
        },
        onRowSelected2(items2) {
            this.selected2 = items2
            let apiURL3 = 'http://localhost:8080/voluntarios/getById_Tarea/' + this.selected2[0].id_Tarea;
            axios.get(apiURL3).then(res => {
                this.items3 = res.data;                      
            }).catch(error => {
                console.log(error)
            });
        },
        onRowSelected3(items3) {
            this.selected3 = items3
        }
    }   
    }
</script>
