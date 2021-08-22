<template>
  <div id="app">
    <div id="nav" >
      <router-link to="/">Inicio</router-link>
    </div>
    <router-view/>
  </div>
</template>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  
  body {
    color: #222;
    background: #fff;
    font: 100% system-ui;
  }
  a{
    color: #ffffff;
  }

  @media (prefers-color-scheme: dark) {
  body {
    color: #eee;
    background: #121212;
  }

  body a {
    color: #809fff;
  }
}
}

#nav {
  padding: 30px;
  background-color: green;

  a {
    font-weight: bold;
    color: #b5b5b5;

    &.router-link-exact-active {
      color: #ffffff;
    }
  }
}
</style>
