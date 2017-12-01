MATCH (n:Franquicia) -[:TIENE_EMEPLEADO]-> (:Persona {nombre:'Roselin'})
RETURN n
