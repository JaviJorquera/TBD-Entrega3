conn = new Mongo("localhost");
db = conn.getDB("DB-Grupo3");
db.dropDatabase();

db.createCollection("Emergencias");
db.createCollection("Tareas");

Emergencia0 = {
    'ID_Emergencia': 0,
    'nombreEmergencia': "Tsumani",
    'requerimientos': "Movilizacion Propia.",
    'latitud_Eme': -70.64505046292976,
    'longitud_Eme': -33.439188052276740,
    'ID_Institucio': 0,
    'ID_Estado': 1
}

Emergencia1 = {
    'ID_Emergencia': 1,
    'nombreEmergencia': "Derrumbe",
    'requerimientos': "Movilizacion Propia.",
    'latitud_Eme': -70.72179164887092,
    'longitud_Eme': -33.403522719349280,
    'ID_Institucio': 1,
    'ID_Estado': 1
}

Emergencia2 = {
    'ID_Emergencia': 2,
    'nombreEmergencia': "Inundacion",
    'requerimientos': "Bote.",
    'latitud_Eme': -70.59335645179831 ,
    'longitud_Eme': -32.853947186053580,
    'ID_Institucio': 2,
    'ID_Estado': 0
}

Tarea0 = {
    'ID_Tarea': 0,
    'nombreTarea': "Limpiar",
    'detalle': "Sacar Escombros",
    'vol_requeridos': 20,
    'ID_Estado': 1,
    'ID_Emergencia': 1
}

Tarea1 = {
    'ID_Tarea': 1,
    'nombreTarea': "Buscar Desaparecidos",
    'detalle': "Buscar dentro del desastre a personas desaparecidas.",
    'vol_requeridos': 100,
    'ID_Estado': 1,
    'ID_Emergencia': 0
}

Tarea2 = {
    'ID_Tarea': 2,
    'nombreTarea': "Extraer Agua",
    'detalle': "Se necesitan personas encargadas de ayudar en la tarea de extarer aguas de las casas abnegadas.",
    'vol_requeridos': 30 ,
    'ID_Estado': 0,
    'ID_Emergencia': 2
}

db.Emergencias.save( Emergencia0 );
db.Emergencias.save( Emergencia1 );
db.Emergencias.save( Emergencia2 );
db.Tareas.save( Tarea0 );
db.Tareas.save( Tarea1 );
db.Tareas.save( Tarea2 );

