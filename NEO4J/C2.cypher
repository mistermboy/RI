MATCH (f:Franquicia {ciudad:'Oviedo'})-[:TIENE_EMEPLEADO]->(p:Persona)
RETURN p
