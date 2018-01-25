MATCH ((p:Persona)-[:ES]->(pu:Puesto) {puesto: “Encargado”})
WHERE NOT ((p)-[:ES_JEFE]->(:Persona))
AND NOT ((p)-[:ES_DUEÑO]->(:Franquicia))
RETURN p
