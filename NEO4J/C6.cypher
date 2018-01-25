MATCH (persona:Persona) -[:ES]->(:Puesto {puesto:'Encargado'}) ,
(puesto:Puesto { puesto: 'Experta en entretenimiento' }),
p = shortestPath((persona)-[*..15]-(puesto))
RETURN p
