<template>
    
    <row justify="center" align="center">
        
        <b-col cols="12" >
            <div class="text-center">
                <emergencia />
            </div>
            <b-card>
                <b-card-title class="headline">
                    Bienvenido a la pagina de Emergencias, seleccione que quiere realizar.
                </b-card-title>
                <b-card-text>
                    <p>Para ver todas las emergencias, Tareas y Voluntarios, haga click aqui.</p>
                    <b-card-actions>
                        <b-spacer />
                        <b-btn variant="success" nuxt to="/ListasInteractivas">Continuar</b-btn>
                    </b-card-actions>
                    <p class="mt-3">
                        Para ver la ubicacion de nuestros voluntarios, haga click aqui.
                    </p>
                    <b-card-actions>
                        <b-spacer />
                        <b-button variant="success" nuxt to="/Mapa">Continuar</b-button>
                    </b-card-actions>
                    <hr class="my-3">
                </b-card-text>
            </b-card>
        </b-col>
    </row>
</template>

<script>
    import Emergencia from '../components/EmergenciasLogo.vue'
    export default{
        components:{
            Emergencia
        }
    }
</script>

<style>
</style>