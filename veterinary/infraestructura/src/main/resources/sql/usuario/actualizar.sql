update usuario
set nombre = :nombre,
    correo = :correo,
	clave = :clave,
	rol = :rol
where id = :id