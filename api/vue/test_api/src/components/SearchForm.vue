<template>
  <div>
    <input type="text" v-model="termoDeBusca" placeholder="Digite sua busca...">
    <button @click="buscar">Buscar</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      termoDeBusca: ''
    };
  },
  methods: {
    buscar() {
      this.$emit('buscar', this.termoDeBusca);
    }
  }
};
</script>
