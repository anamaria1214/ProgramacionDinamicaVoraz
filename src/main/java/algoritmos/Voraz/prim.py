# -----------------------------------------------
# Algoritmo de Prim - Árbol de Recubrimiento Mínimo
# -----------------------------------------------

import heapq  # para usar cola de prioridad (min-heap)

def prim(grafo, inicio):
    """
    Implementa el algoritmo de Prim para obtener el Árbol de Recubrimiento Mínimo (MST).

    :param grafo: diccionario {nodo: [(peso, vecino), ...]}
    :param inicio: nodo inicial
    :return: lista de aristas del MST y peso total
    """
    visitados = set()
    aristas_mst = []
    peso_total = 0
    cola = [(0, inicio, None)]  # (peso, nodo_actual, nodo_previo)

    while cola:
        peso, actual, previo = heapq.heappop(cola)

        if actual in visitados:
            continue

        visitados.add(actual)
        if previo is not None:
            aristas_mst.append((previo, actual, peso))
            peso_total += peso

        for peso_vecino, vecino in grafo[actual]:
            if vecino not in visitados:
                heapq.heappush(cola, (peso_vecino, vecino, actual))

    return aristas_mst, peso_total


# -----------------------------------------------
# Programa principal (main)
# -----------------------------------------------
def main():
    grafo = {
        'A': [(2, 'B'), (3, 'C')],
        'B': [(2, 'A'), (4, 'C'), (5, 'D')],
        'C': [(3, 'A'), (4, 'B'), (1, 'D')],
        'D': [(5, 'B'), (1, 'C')]
    }

    mst, peso = prim(grafo, 'A')

    print("=== Árbol de Recubrimiento Mínimo (Algoritmo de Prim) ===")
    print("Aristas seleccionadas:")
    for u, v, w in mst:
        print(f"{u} - {v} : {w}")
    print(f"\nPeso total del árbol: {peso}")


if __name__ == "__main__":
    main()
