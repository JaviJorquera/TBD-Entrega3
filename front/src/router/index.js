import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Emergencias from '../views/ListasInteractivas.vue'
import Mapa from '../views/Mapa.vue'
//import NewDog from '../views/NewDog.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/ListasInteractivas',
    name: 'ListasInteractivas',
    component: Emergencias
  },
  {
    path: '/Mapa',
    name: 'Mapa',
    component: Mapa
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
