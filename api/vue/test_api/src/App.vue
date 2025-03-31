<template>
  <div>
    <SearchForm @buscar="buscarOperadoras"></SearchForm>
    <SearchResults :resultados="resultados"></SearchResults>
  </div>
</template>

<script>
import axios from 'axios';
import SearchForm from './components/SearchForm.vue';
import SearchResults from './components/SearchResults.vue';

export default {
  components: {
    SearchForm,
    SearchResults
  },
  data() {
    return {
      resultados: []
    };
  },
  methods: {
    async buscarOperadoras(termoDeBusca) {
      try {
        const response = await axios.get(`http://localhost:5000/buscar?termo=${termoDeBusca}`);
        this.resultados = response.data;
      } catch (error) {
        console.error('Erro ao buscar operadoras:', error);
      }
    }
  }
};
</script>
