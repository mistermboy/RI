MATCH (leon:Franquicia { ciudad: 'León' }),(lugones:Franquicia {ciudad: ‘Lugones’}),
p = shortestPath((leon)-[*..15]-(lugones))
RETURN p
