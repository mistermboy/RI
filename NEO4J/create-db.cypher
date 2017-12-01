// Creando nodos de tipo Persona.
CREATE (Paloma:Persona {nombre:'Paloma', apellidos: ‘Mas Pellicer'})
CREATE (Eustaquio:Persona {nombre:'Eustaquio'})
CREATE (Muriel:Persona {nombre:'Muriel'})
CREATE (Agallas:Persona {nombre:'Agallas'})
CREATE (Roselin:Persona {nombre:'Roselin'})
CREATE (Carlos:Persona {nombre:'Carlos'})
CREATE (María:Persona {nombre:'María'})
CREATE (Pepa:Persona {nombre:'Pepa'})
CREATE (Aria:Persona {nombre:'Aria'})
CREATE (Álvaro:Persona {nombre:'Álvaro'})
CREATE (Encarni:Persona {nombre:'Encarni'})
CREATE (Lucía:Persona {nombre:'Lucía'})
CREATE (Vinuesa:Persona {nombre:'Vinuesa'})
CREATE (Alvizu:Persona {nombre:'Alvizu'})
CREATE (Tuya:Persona {nombre:'Tuya'})
CREATE (Caval:Persona {nombre:'Caval'})

// Creando nodos de tipo Franquicia.
CREATE (Leon:Franquicia {ciudad:'León', nombre :'Franquicia de León'})
CREATE (Madrid:Franquicia {ciudad:'Madrid', nombre :'Franquicia de Madrid'})
CREATE (Lugones:Franquicia {ciudad:'Lugones', nombre :'Franquicia de Lugones'})
CREATE (CAU:Franquicia {ciudad:'Oviedo', nombre :'Franquicia del CAU'})

// Creando nodos de tipo Puesto
CREATE (Encargado:Puesto {puesto:'Encargado'})
CREATE (Limpiador:Puesto {puesto:'Limpiador'})
CREATE (Cocinero:Puesto {puesto:'Cocinero'})
CREATE (Cajero:Puesto {puesto:'Cajero'})
CREATE (ExpertaEnEntretenimiento:Puesto {puesto:'Experta en entretenimiento'})

// Creando relaciones entre nodos.
CREATE
  (Paloma)-[:ES]->(ExpertaEnEntretenimiento),
  (Aria)-[:ES]->(ExpertaEnEntretenimiento),
  (Aria)-[:ES]->(Encargado),
  (Eustaquio)-[:ES]->(Encargado),
  (María)-[:ES]->(Encargado),
  (Vinuesa)-[:ES]->(Encargado),
  (Alvizu)-[:ES]->(Cajero),
  (Lucía)-[:ES]->(Cajero),
  (Muriel)-[:ES]->(Cajero),
  (Álvaro)-[:ES]->(Cajero),
  (Agallas)-[:ES]->(Cocinero),
  (Roselin)-[:ES]->(Cocinero),
  (Carlos)-[:ES]->(Cocinero),
  (Álvaro)-[:ES]->(Cocinero),
  (Tuya)-[:ES]->(Cocinero),
  (Encarni)-[:ES]->(Limpiador),
  (Caval)-[:ES]->(Limpiador),
  (Pepa)-[:ES]->(Limpiador),
  (Leon)-[:ES_TRABAJADOR]->(Muriel),
  (Leon)-[:ES_TRABAJADOR]->(Roselin),
  (Leon)-[:ES_TRABAJADOR]->(Agallas),
  (Leon)-[:ES_TRABAJADOR]->(Eustaquio),
  (Leon)-[:ES_TRABAJADOR]->(Paloma),
  (CAU)-[:ES_TRABAJADOR]->(Vinuesa),
  (CAU)-[:ES_TRABAJADOR]->(Alvizu),
  (CAU)-[:ES_TRABAJADOR]->(Tuya),
  (CAU)-[:ES_TRABAJADOR]->(Caval),
  (Madrid)-[:ES_TRABAJADOR]->(Aria),
  (Madrid)-[:ES_TRABAJADOR]->(Pepa),
  (Madrid)-[:ES_TRABAJADOR]->(Álvaro),
  (Lugones)-[:ES_TRABAJADOR]->(Encarni),
  (Lugones)-[:ES_TRABAJADOR]->(María),
  (Lugones)-[:ES_TRABAJADOR]->(Roselin),
  (Lugones)-[:ES_TRABAJADOR]->(Lucía),
  (Eustaquio)-[:ES_DUEÑO]->(Leon),
  (Aria)-[:ES_DUEÑO]->(Madrid),
  (Carlos)-[:ES_DUEÑO]->(Lugones),
  (Vinuesa)-[:ES_DUEÑO]->(CAU),
  (Eustaquio)-[:ES_JEFE]->(Paloma),
  (Eustaquio)-[:ES_JEFE]->(Muriel),
  (Eustaquio)-[:ES_JEFE]->(Roselin),
  (Eustaquio)-[:ES_JEFE]->(Agallas),
  (Aria)-[:ES_JEFE]->(Pepa),
  (Aria)-[:ES_JEFE]->(Álvaro),
  (Carlos)-[:ES_JEFE]->(María),
  (Carlos)-[:ES_JEFE]->(Encarni),
  (Carlos)-[:ES_JEFE]->(Lucía),
  (Carlos)-[:ES_JEFE]->(Roselin),
  (Vinuesa)-[:ES_JEFE]->(Alvizu),
  (Alvizu)-[:ES_JEFE]->(Tuya),
  (Tuya)-[:ES_JEFE]->(Caval)
