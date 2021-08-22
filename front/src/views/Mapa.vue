<template>
    <b-container fluid>
        <b-row>
            <b-col style="border-style: groove;" cols="2">
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
                <div class="mt-3 py-2" style="background-color: #e6e6e6; color: black;" >
                    <h4 class="py-2" > Voluntarios:</h4> 
                    <ul>               
                        <li v-for="voluntario in items2" :key="voluntario._id" >
                            {{voluntario.nombreVoluntario}}.
                        </li>
                    </ul>
                </div>
            </b-col >
            <b-col style="border-style: groove;">
                <!-- MAPA -->
                <l-map :zoom="zoom" :center="center" style="height: 640px; width: 100%" >
                    <l-tile-layer :url="url" :attribution="attribution" />
                    <l-marker v-for="voluntario in items2" :key="voluntario._id" :lat-lng="[voluntario.latitud,voluntario.longitud]" :icon="icon">
                        <l-popup>{{voluntario.nombreVoluntario}}</l-popup>
                    </l-marker>
                </l-map>
            </b-col>
        </b-row>
    </b-container >
</template>

<script>
    import axios from "axios";
    import { latLng,Icon } from "leaflet";
    import { LMap, LTileLayer, LMarker , LPopup } from 'vue2-leaflet';
    delete Icon.Default.prototype._getIconUrl;
    Icon.Default.mergeOptions({ iconRetinaUrl: require('leaflet/dist/images/marker-icon-2x.png'), iconUrl: require('leaflet/dist/images/marker-icon.png'), shadowUrl: require('leaflet/dist/images/marker-shadow.png'),});
    
    export default {
        name: "Icon",
        components: {
            LMap,
            LTileLayer,
            LMarker,
            LPopup,
        },
        
        data() {
            return {
                zoom: 4,
                center: latLng(-38.719, -72.478),
                url: 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
                fields: ['nombreEmergencia'],
                items: [],
                items2: [],
                selectMode: 'single',
                selected: [{}],
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
                let apiURL2 = 'http://localhost:8080/voluntarios/getVoluntariosByID_Emergencia/' + this.selected[0].id_Emergencia;
                axios.get(apiURL2).then(res => {
                    this.items2 = res.data;                      
                }).catch(error => {
                    console.log(error)
                }); 
            }
        }   
    }
</script>