# -----------------------------------------------
# Algoritmo voraz - Mochila Fraccionaria
# -----------------------------------------------

def mochila_fraccionaria(pesos, valores, capacidad):
    """
    Resuelve el problema de la mochila fraccionaria usando un algoritmo voraz.

    :param pesos: lista con los pesos de los objetos
    :param valores: lista con los valores de los objetos
    :param capacidad: capacidad máxima de la mochila
    :return: valor máximo total y lista de objetos seleccionados con fracciones
    """
    n = len(valores)
    # Calcular el valor por unidad de peso
    ratio = [(valores[i] / pesos[i], pesos[i], valores[i]) for i in range(n)]
    
    # Ordenar por valor/peso en orden descendente
    ratio.sort(reverse=True, key=lambda x: x[0])
    
    valor_total = 0.0
    capacidad_restante = capacidad
    seleccion = []

    # Selección de objetos según la estrategia voraz
    for r, peso, valor in ratio:
        if capacidad_restante <= 0:
            break
        if peso <= capacidad_restante:
            # Se toma todo el objeto
            valor_total += valor
            capacidad_restante -= peso
            seleccion.append((peso, valor, 1.0))
        else:
            # Se toma una fracción del objeto
            fraccion = capacidad_restante / peso
            valor_total += valor * fraccion
            seleccion.append((peso, valor, fraccion))
            capacidad_restante = 0

    return valor_total, seleccion


# -----------------------------------------------
# Programa principal para probar el algoritmo
# -----------------------------------------------
def main():
    pesos = [10, 20, 30]
    valores = [60, 100, 120]
    capacidad = 50

    valor_maximo, seleccion = mochila_fraccionaria(pesos, valores, capacidad)

    print("=== Mochila Fraccionaria (Algoritmo Voraz) ===")
    print(f"Capacidad máxima de la mochila: {capacidad}")
    print(f"Valor máximo obtenido: {valor_maximo:.2f}\n")
    print("Objetos seleccionados:")
    for peso, valor, fraccion in seleccion:
        print(f" - Peso: {peso}, Valor: {valor}, Fracción tomada: {fraccion:.2f}")


# Ejecutar el programa
if __name__ == "__main__":
    main()
