MATCH (p:Persona) -[:ES_JEFE]-> (:Persona)
WHERE p.nombre STARTS WITH 'A'
AND NOT (p)-[:ES_DUEÑO]->(:Franquicia)
RETURN p
