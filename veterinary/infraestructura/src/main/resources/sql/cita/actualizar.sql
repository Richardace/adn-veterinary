update cita
set idUsuario = :idUsuario,
	fecha = :fecha,
	hora = :hora,
	precio = :precio,
	notas = :notas
where id = :id