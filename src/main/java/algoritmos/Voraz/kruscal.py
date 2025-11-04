class DisjointSet:
    def __init__(self, vertices):
        self.parent = {v: v for v in vertices}
        self.rank = {v: 0 for v in vertices}

    def find(self, v):
        if self.parent[v] != v:
            self.parent[v] = self.find(self.parent[v])  # compresión de caminos
        return self.parent[v]

    def union(self, u, v):
        root_u = self.find(u)
        root_v = self.find(v)

        if root_u != root_v:
            if self.rank[root_u] < self.rank[root_v]:
                self.parent[root_u] = root_v
            elif self.rank[root_u] > self.rank[root_v]:
                self.parent[root_v] = root_u
            else:
                self.parent[root_v] = root_u
                self.rank[root_u] += 1


def kruskal(vertices, edges):
    edges.sort(key=lambda x: x[2])  # ordenar por peso
    ds = DisjointSet(vertices)
    mst = []

    for u, v, w in edges:
        if ds.find(u) != ds.find(v):
            mst.append((u, v, w))
            ds.union(u, v)

    return mst


def main():
    vertices = ['A', 'B', 'C', 'D', 'E']
    edges = [
        ('A', 'B', 2),
        ('A', 'C', 3),
        ('B', 'C', 1),
        ('B', 'D', 4),
        ('C', 'D', 5),
        ('C', 'E', 6),
        ('D', 'E', 7)
    ]

    mst = kruskal(vertices, edges)

    print("Aristas del Árbol de Recubrimiento Mínimo:")
    total = sum([w for (_, _, w) in mst])
    for u, v, w in mst:
        print(f"{u} - {v} (peso {w})")
    print(f"Costo total: {total}")


if __name__ == "__main__":
    main()
