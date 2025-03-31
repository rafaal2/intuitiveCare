<template>
  <div>
    <ul>
      <li v-for="resultado in resultados" :key="resultado.Registro_ANS">
        {{ resultado.Razao_Social }} - {{ resultado.Modalidade }}
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  props: {
    resultados: Array
  }
};
</script>
