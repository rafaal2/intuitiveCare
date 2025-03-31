import os
from flask import Flask, request, jsonify
from flask_cors import CORS
import csv

app = Flask(__name__)
CORS(app)

def carregar_dados_csv(caminho_arquivo):
    with open(caminho_arquivo, newline='', encoding='utf-8') as csvfile:
        reader = csv.DictReader(csvfile, delimiter=';')
        return list(reader)

caminho_arquivo_csv = r"C:\Users\rafae\Downloads\DadosTeste\Relatorio_cadop.csv"
dados_operadoras = carregar_dados_csv(caminho_arquivo_csv)

@app.route('/buscar', methods=['GET'])
def buscar_operadoras():
    termo = request.args.get('termo')
    resultados = []

    for operadora in dados_operadoras:
        for campo in operadora.values():
            if termo.lower() in str(campo).lower():
                resultados.append(operadora)
                break

    return jsonify(resultados)

if __name__ == '__main__':
    app.run(debug=True)
