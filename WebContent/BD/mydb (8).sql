-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.jspmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-10-2017 a las 08:24:47
-- Versión del servidor: 5.6.17
-- Versión de PHP: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `mydb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actualizacion`
--

CREATE TABLE IF NOT EXISTS `actualizacion` (
  `idActualizacion` int(11) NOT NULL AUTO_INCREMENT,
  `hora` time NOT NULL,
  `codigo` varchar(45) NOT NULL,
  `independiente` varchar(45) NOT NULL,
  `comentario_idcomentario` int(11) NOT NULL,
  `comentario_cliente_idcliente` int(11) NOT NULL,
  `independiente_ndocumento` int(11) NOT NULL,
  `independiente_membresia_idmembresia` int(11) NOT NULL,
  PRIMARY KEY (`idActualizacion`),
  KEY `fk_Actualizacion_comentario1_idx` (`comentario_idcomentario`,`comentario_cliente_idcliente`),
  KEY `fk_Actualizacion_independiente1_idx` (`independiente_ndocumento`,`independiente_membresia_idmembresia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE IF NOT EXISTS `categorias` (
  `idcategorias` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_categorias` varchar(45) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  PRIMARY KEY (`idcategorias`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`idcategorias`, `tipo_categorias`, `categoria`) VALUES
(1, 'servicios hogar', 'plomeria'),
(2, 'Software', 'Diseño Web'),
(3, 'Construcion', 'oficial');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE IF NOT EXISTS `cliente` (
  `idcliente` int(11) NOT NULL AUTO_INCREMENT,
  `primer_nombre` varchar(45) NOT NULL,
  `segundo_nombre` varchar(45) NOT NULL,
  `primer_apellido` varchar(45) NOT NULL,
  `segundo_apellido` varchar(45) NOT NULL,
  `telefono` int(11) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `usuario` varchar(10) NOT NULL,
  `password` varchar(100) NOT NULL,
  `rol_idrol` int(11) NOT NULL,
  PRIMARY KEY (`idcliente`),
  KEY `fk_cliente_rol1_idx` (`rol_idrol`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`idcliente`, `primer_nombre`, `segundo_nombre`, `primer_apellido`, `segundo_apellido`, `telefono`, `correo`, `usuario`, `password`, `rol_idrol`) VALUES
(3, 'maria', 'eugenia', 'cordoba', 'palacios', 5844821, 'mari@gmail.com', 'mari', '123', 2),
(4, 'sofia', 'sofia', 'moreno', 'orozco', 12345, 'sofia@gmail.com', '', '123', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentario`
--

CREATE TABLE IF NOT EXISTS `comentario` (
  `idcomentario` int(11) NOT NULL AUTO_INCREMENT,
  `hora` varchar(45) NOT NULL,
  `actualizacion` varchar(50) NOT NULL,
  `codigo` varchar(40) NOT NULL,
  `cliente_idcliente` int(11) NOT NULL,
  `reputacion_idreputacion` int(11) NOT NULL,
  PRIMARY KEY (`idcomentario`,`cliente_idcliente`),
  KEY `fk_comentario_cliente1_idx` (`cliente_idcliente`),
  KEY `fk_comentario_reputacion1_idx` (`reputacion_idreputacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cotizacion`
--

CREATE TABLE IF NOT EXISTS `cotizacion` (
  `idcotizacion` int(11) NOT NULL AUTO_INCREMENT,
  `independiente` varchar(45) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  `servicio` varchar(45) NOT NULL,
  `cliente` varchar(45) NOT NULL,
  `fecha` varchar(45) NOT NULL,
  `descripción` varchar(45) NOT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `valor_hora` int(11) DEFAULT NULL,
  `valor_bruto` int(11) DEFAULT NULL,
  `valor_total` int(11) NOT NULL,
  `cliente_idcliente` int(11) NOT NULL,
  PRIMARY KEY (`idcotizacion`),
  KEY `fk_cotizacion_cliente1_idx` (`cliente_idcliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cursos`
--

CREATE TABLE IF NOT EXISTS `cursos` (
  `idcursos` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  `independiente_ndocumento` int(11) NOT NULL,
  `independiente_membresia_idmembresia` int(11) NOT NULL,
  PRIMARY KEY (`idcursos`,`independiente_ndocumento`,`independiente_membresia_idmembresia`),
  KEY `fk_cursos_independiente1_idx` (`independiente_ndocumento`,`independiente_membresia_idmembresia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `departamento`
--

CREATE TABLE IF NOT EXISTS `departamento` (
  `iddepartamento` int(11) NOT NULL,
  `municipio_idmunicipio` int(11) NOT NULL,
  PRIMARY KEY (`iddepartamento`),
  KEY `fk_departamento_municipio1_idx` (`municipio_idmunicipio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_cuenta`
--

CREATE TABLE IF NOT EXISTS `estado_cuenta` (
  `idestado_cuenta` int(11) NOT NULL AUTO_INCREMENT,
  `membresia` int(11) NOT NULL,
  `tipo_membresia` varchar(45) NOT NULL,
  `estado_pago` varchar(45) NOT NULL,
  `membresia_idmembresia` int(11) NOT NULL,
  PRIMARY KEY (`idestado_cuenta`),
  KEY `fk_estado_cuenta_membresia1_idx` (`membresia_idmembresia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE IF NOT EXISTS `factura` (
  `idfactura` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` int(11) DEFAULT NULL,
  `independiente` varchar(45) NOT NULL,
  `servicio` varchar(45) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  `valor_bruto` int(11) DEFAULT NULL,
  `valor_neto` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `estado_pago` varchar(20) NOT NULL,
  `estado_cuenta_idestado_cuenta` int(11) NOT NULL,
  PRIMARY KEY (`idfactura`),
  KEY `fk_factura_estado_cuenta1_idx` (`estado_cuenta_idestado_cuenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `imagenes_independiente`
--

CREATE TABLE IF NOT EXISTS `imagenes_independiente` (
  `idimagenes_independiente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `servicio` varchar(45) NOT NULL,
  `imagen` varchar(200) NOT NULL,
  `independiente_ndocumento` int(11) NOT NULL,
  PRIMARY KEY (`idimagenes_independiente`),
  KEY `fk_imagenes_independiente_independiente1_idx` (`independiente_ndocumento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `independiente`
--

CREATE TABLE IF NOT EXISTS `independiente` (
  `idindependiente` int(13) NOT NULL AUTO_INCREMENT,
  `tipo_documento` varchar(40) NOT NULL,
  `nombres` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `fecha_nacimiento` varchar(12) NOT NULL,
  `fecha_expedicion` varchar(12) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `telefono` int(11) NOT NULL,
  `celular` int(11) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `ocupacion` varchar(45) NOT NULL,
  `pais_residencia` varchar(50) NOT NULL,
  `departamento_residencia` varchar(50) NOT NULL,
  `ciudad_residencia` varchar(50) NOT NULL,
  `referencia_personal1` varchar(80) NOT NULL,
  `referencia_personal2` varchar(80) NOT NULL,
  `referencia_ocupacional1` varchar(80) NOT NULL,
  `referencia_ocupacional2` varchar(80) NOT NULL,
  `num_personal1` int(11) NOT NULL,
  `num_personal2` int(11) NOT NULL,
  `num_ocupacional1` int(11) NOT NULL,
  `num_ocupacional2` int(11) NOT NULL,
  `pass` varchar(15) NOT NULL,
  `foto_perfil` varchar(200) NOT NULL,
  `nombre_foto` varchar(30) NOT NULL,
  `rol_idrol` int(11) NOT NULL,
  `reputacion_idreputacion` int(11) NOT NULL,
  `membresia_idmembresia` int(11) NOT NULL,
  `categorias_idcategorias` int(11) NOT NULL,
  PRIMARY KEY (`idindependiente`,`membresia_idmembresia`),
  KEY `fk_independiente_rol1_idx` (`rol_idrol`),
  KEY `fk_independiente_reputacion1_idx` (`reputacion_idreputacion`),
  KEY `fk_independiente_membresia1_idx` (`membresia_idmembresia`),
  KEY `fk_independiente_categorias1_idx` (`categorias_idcategorias`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=41 ;

--
-- Volcado de datos para la tabla `independiente`
--

INSERT INTO `independiente` (`idindependiente`, `tipo_documento`, `nombres`, `apellidos`, `fecha_nacimiento`, `fecha_expedicion`, `correo`, `telefono`, `celular`, `direccion`, `ocupacion`, `pais_residencia`, `departamento_residencia`, `ciudad_residencia`, `referencia_personal1`, `referencia_personal2`, `referencia_ocupacional1`, `referencia_ocupacional2`, `num_personal1`, `num_personal2`, `num_ocupacional1`, `num_ocupacional2`, `pass`, `foto_perfil`, `nombre_foto`, `rol_idrol`, `reputacion_idreputacion`, `membresia_idmembresia`, `categorias_idcategorias`) VALUES
(30, '', 'Fredy', 'Moreno', '', '', 'famocor@hotmail.com', 1, 1, '', '', '', '', '', '', '', '', '', 1, 1, 1, 1, '123', '../imgperfiles/cardio.png', 'cardio.png', 1, 1, 1, 1),
(31, '', 'Kateryne', 'Orozco', '', '', 'kateryne2555@gmail.com', 1, 1, '', '', '', '', 'Envigado', '', '', '', '', 1, 1, 1, 1, '123', '../imgperfiles/bigote.jpg', 'bigote.jpg', 1, 1, 2, 1),
(33, '', 'juan', 'moreno', '', '', 'juandamoreno@hotmail.com', 1, 1, '', '', '', '', '', '', '', '', '', 1, 1, 1, 1, '123456', '../imgperfiles/caballero.png', 'caballero.png', 1, 1, 2, 2),
(34, '', 'juan david', 'Moreno', '', '', 'jjmoreno@hotmail.com', 1, 1, '', '', '', '', '', '', '', '', '', 1, 1, 1, 1, '123', '../imgperfiles/TeOcupo2.png', 'TeOcupo2.png', 1, 1, 2, 2),
(35, '', 'juan david', 'Moreno', '', '', 'jjmoreno@hotmail.com', 1, 1, '', '', '', '', '', '', '', '', '', 1, 1, 1, 1, '123', '../imgperfiles/TeOcupo2.png', 'TeOcupo2.png', 1, 1, 2, 2),
(36, '', 'eliseo', 'cuello', '', '', 'ff@h.com', 1, 1, '', '', '', '', '', '', '', '', '', 1, 1, 1, 1, '123', '../imgperfiles/DIAS_DE_SPA.jpg', 'DIAS_DE_SPA.jpg', 1, 1, 2, 2),
(37, '', 'samir', 'casiani', '', '', 'samir@gmail.com', 1, 1, '', '', '', '', '', '', '', '', '', 1, 1, 1, 1, '123', '../imgperfiles/38.jpg', '38.jpg', 1, 1, 2, 2),
(38, '', 'jose', 'montoya', '', '', 'jezzy@gmail.com', 1, 1, '', '', '', '', 'medellin', '', '', '', '', 1, 1, 1, 1, '123', '../imgperfiles/38.jpg', '38.jpg', 1, 1, 2, 1),
(39, '', 'jose', 'montoya', '', '', 'jezzy@gmail.com', 1, 1, '', '', '', '', 'medellin', '', '', '', '', 1, 1, 1, 1, '122', '../imgperfiles/75.jpg', '75.jpg', 1, 1, 2, 1),
(40, '', 'Kateryne', 'Orozco Cifuentes', '', '', 'kate@gmail.com', 1, 1, '', '', '', '', 'medellin', '', '', '', '', 1, 1, 1, 1, '123', '../imgperfiles/3.jpg', '3.jpg', 1, 1, 1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `membresia`
--

CREATE TABLE IF NOT EXISTS `membresia` (
  `idmembresia` int(11) NOT NULL AUTO_INCREMENT,
  `membresia` varchar(45) NOT NULL,
  `tipo_membresia` varchar(45) NOT NULL,
  PRIMARY KEY (`idmembresia`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `membresia`
--

INSERT INTO `membresia` (`idmembresia`, `membresia`, `tipo_membresia`) VALUES
(1, '1', 'oro'),
(2, '2', 'plata'),
(3, '3', 'bronce'),
(4, '4', 'free');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `miscategorias`
--

CREATE TABLE IF NOT EXISTS `miscategorias` (
  `idmiscategorias` int(11) NOT NULL AUTO_INCREMENT,
  `independiente_ndocumento` int(11) NOT NULL,
  `categorias_idcategorias` int(11) NOT NULL,
  PRIMARY KEY (`idmiscategorias`),
  KEY `fk_miscategorias_independiente1_idx` (`independiente_ndocumento`),
  KEY `fk_miscategorias_categorias1_idx` (`categorias_idcategorias`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `misfotos`
--

CREATE TABLE IF NOT EXISTS `misfotos` (
  `idmisfotos` int(11) NOT NULL AUTO_INCREMENT,
  `ruta` varchar(100) NOT NULL,
  `tipo_foto` varchar(20) NOT NULL,
  `imagen` varchar(200) NOT NULL,
  `fecha` varchar(45) NOT NULL,
  `codigo` varchar(10) NOT NULL,
  `servicio_idservicio` int(11) NOT NULL,
  `independiente_ndocumento` int(11) NOT NULL,
  PRIMARY KEY (`idmisfotos`),
  KEY `fk_misfotos_independiente1_idx` (`independiente_ndocumento`),
  KEY `servicio_idservicio` (`servicio_idservicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `municipio`
--

CREATE TABLE IF NOT EXISTS `municipio` (
  `idmunicipio` int(11) NOT NULL,
  `independiente_ndocumento` int(11) NOT NULL,
  `estado_cuenta_idestado_cuenta` int(11) NOT NULL,
  PRIMARY KEY (`idmunicipio`,`estado_cuenta_idestado_cuenta`),
  KEY `fk_municipio_independiente1_idx` (`independiente_ndocumento`),
  KEY `fk_municipio_estado_cuenta1_idx` (`estado_cuenta_idestado_cuenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pais`
--

CREATE TABLE IF NOT EXISTS `pais` (
  `idpais` int(11) NOT NULL,
  `departamento_iddepartamento` int(11) NOT NULL,
  PRIMARY KEY (`idpais`),
  KEY `fk_pais_departamento1_idx` (`departamento_iddepartamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `publicaciones`
--

CREATE TABLE IF NOT EXISTS `publicaciones` (
  `idpublicaciones` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(60) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `fecha` date NOT NULL,
  `precio` int(20) NOT NULL,
  `independiente_ndocumento` int(11) NOT NULL,
  `categorias_idcategorias` int(11) NOT NULL,
  `servicio_idservicio` int(11) NOT NULL,
  PRIMARY KEY (`idpublicaciones`,`independiente_ndocumento`),
  KEY `fk_publicaciones_independiente1_idx` (`independiente_ndocumento`),
  KEY `fk_publicaciones_categorias1_idx` (`categorias_idcategorias`),
  KEY `fk_publicaciones_subcategorias1_idx` (`servicio_idservicio`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Volcado de datos para la tabla `publicaciones`
--

INSERT INTO `publicaciones` (`idpublicaciones`, `titulo`, `descripcion`, `fecha`, `precio`, `independiente_ndocumento`, `categorias_idcategorias`, `servicio_idservicio`) VALUES
(1, 'hiyi', 'uiyui', '2017-09-24', 564356, 38, 1, 2),
(2, 'gjghj', 'ghjhgjgh', '2017-09-24', 54645, 38, 1, 2),
(3, 'Arreglo baÃ±os', 'Excelente servicio, cambio valvulas', '2017-09-24', 50000, 38, 1, 2),
(4, 'Arreglo chapas', 'Excelente servicio, full Hd', '2017-09-24', 654989, 38, 1, 2),
(5, 'Aseo por dÃ­a a casas', 'Excelente servicio, limpieza garantizada', '2017-09-24', 1000, 31, 1, 1),
(6, 'Destaqueo baÃ±os', 'Excelente servicios, full hd', '2017-09-24', 1000, 31, 1, 2),
(7, 'Copias de llaves a domicilio', 'En menos de un minuto', '2017-09-24', 2000, 31, 1, 2),
(8, 'Modelos Entidad Relacion', 'Analista Jr, BD.', '2017-09-24', 7889, 40, 2, 4),
(9, 'Paginas Web', 'El Mejor Servicio, ofresco 3 planes , basico,', '2017-09-24', 12354, 40, 2, 3),
(10, 'ASEO PARA CASAS', 'TRABAJO POR DIA', '2017-09-25', 1234, 38, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reputacion`
--

CREATE TABLE IF NOT EXISTS `reputacion` (
  `idreputacion` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idreputacion`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `reputacion`
--

INSERT INTO `reputacion` (`idreputacion`) VALUES
(1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE IF NOT EXISTS `rol` (
  `idrol` int(11) NOT NULL AUTO_INCREMENT,
  `usuarios` varchar(20) NOT NULL,
  `independiente` varchar(20) NOT NULL,
  `cliente` varchar(20) NOT NULL,
  `manager` varchar(10) NOT NULL,
  PRIMARY KEY (`idrol`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`idrol`, `usuarios`, `independiente`, `cliente`, `manager`) VALUES
(1, 'independiente', '1', '0', '0'),
(2, 'cliente', '0', '1', '0'),
(3, 'manager', '0', '0', '1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio`
--

CREATE TABLE IF NOT EXISTS `servicio` (
  `idservicio` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `tipo_servicio` varchar(45) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  `categorias_idcategorias` int(11) NOT NULL,
  `independiente_ndocumento` int(11) NOT NULL,
  PRIMARY KEY (`idservicio`),
  KEY `fk_servicio_independiente1_idx` (`independiente_ndocumento`),
  KEY `fk_servicio_categorias1_idx` (`categorias_idcategorias`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `servicio`
--

INSERT INTO `servicio` (`idservicio`, `nombre`, `tipo_servicio`, `categoria`, `categorias_idcategorias`, `independiente_ndocumento`) VALUES
(1, 'Aseo', 'servicios varios', 'aseadora', 1, 30),
(2, 'Plomero', 'plomeros', 'plomeria', 1, 30),
(3, 'Diseno web', '', '', 2, 31),
(4, 'Analisis BD', '', '', 2, 31),
(5, 'Desarrollo', '', '', 2, 33);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `subcategorias`
--

CREATE TABLE IF NOT EXISTS `subcategorias` (
  `idsubcategorias` int(11) NOT NULL AUTO_INCREMENT,
  `subcategoria` varchar(45) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  `codigo` varchar(45) DEFAULT NULL,
  `categorias_idcategorias` int(11) NOT NULL,
  PRIMARY KEY (`idsubcategorias`,`categorias_idcategorias`),
  KEY `fk_subcategorias_categorias1_idx` (`categorias_idcategorias`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `n_documento` int(11) NOT NULL,
  `tipo_documento` varchar(50) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `fecha_expedicion` date NOT NULL,
  `primer_nombre` varchar(45) NOT NULL,
  `segundo_nombre` varchar(45) DEFAULT NULL,
  `primer_apellido` varchar(45) NOT NULL,
  `segundo_apellido` varchar(45) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `telefono` int(11) DEFAULT NULL,
  `celular` int(11) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `rol_idrol` int(11) NOT NULL,
  PRIMARY KEY (`idusuario`),
  UNIQUE KEY `n_documento_UNIQUE` (`n_documento`),
  KEY `fk_usuario_rol1_idx` (`rol_idrol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `actualizacion`
--
ALTER TABLE `actualizacion`
  ADD CONSTRAINT `fk_Actualizacion_comentario1` FOREIGN KEY (`comentario_idcomentario`, `comentario_cliente_idcliente`) REFERENCES `comentario` (`idcomentario`, `cliente_idcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Actualizacion_independiente1` FOREIGN KEY (`independiente_ndocumento`, `independiente_membresia_idmembresia`) REFERENCES `independiente` (`idindependiente`, `membresia_idmembresia`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `fk_cliente_rol1` FOREIGN KEY (`rol_idrol`) REFERENCES `rol` (`idrol`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD CONSTRAINT `fk_comentario_cliente1` FOREIGN KEY (`cliente_idcliente`) REFERENCES `cliente` (`idcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_comentario_reputacion1` FOREIGN KEY (`reputacion_idreputacion`) REFERENCES `reputacion` (`idreputacion`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `cotizacion`
--
ALTER TABLE `cotizacion`
  ADD CONSTRAINT `fk_cotizacion_cliente1` FOREIGN KEY (`cliente_idcliente`) REFERENCES `cliente` (`idcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `cursos`
--
ALTER TABLE `cursos`
  ADD CONSTRAINT `fk_cursos_independiente1` FOREIGN KEY (`independiente_ndocumento`, `independiente_membresia_idmembresia`) REFERENCES `independiente` (`idindependiente`, `membresia_idmembresia`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `departamento`
--
ALTER TABLE `departamento`
  ADD CONSTRAINT `fk_departamento_municipio1` FOREIGN KEY (`municipio_idmunicipio`) REFERENCES `municipio` (`idmunicipio`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `estado_cuenta`
--
ALTER TABLE `estado_cuenta`
  ADD CONSTRAINT `fk_estado_cuenta_membresia1` FOREIGN KEY (`membresia_idmembresia`) REFERENCES `membresia` (`idmembresia`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `fk_factura_estado_cuenta1` FOREIGN KEY (`estado_cuenta_idestado_cuenta`) REFERENCES `estado_cuenta` (`idestado_cuenta`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `imagenes_independiente`
--
ALTER TABLE `imagenes_independiente`
  ADD CONSTRAINT `fk_imagenes_independiente_independiente1` FOREIGN KEY (`independiente_ndocumento`) REFERENCES `independiente` (`idindependiente`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `independiente`
--
ALTER TABLE `independiente`
  ADD CONSTRAINT `fk_independiente_categorias1` FOREIGN KEY (`categorias_idcategorias`) REFERENCES `categorias` (`idcategorias`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_independiente_membresia1` FOREIGN KEY (`membresia_idmembresia`) REFERENCES `membresia` (`idmembresia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_independiente_reputacion1` FOREIGN KEY (`reputacion_idreputacion`) REFERENCES `reputacion` (`idreputacion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_independiente_rol1` FOREIGN KEY (`rol_idrol`) REFERENCES `rol` (`idrol`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `miscategorias`
--
ALTER TABLE `miscategorias`
  ADD CONSTRAINT `fk_miscategorias_categorias1` FOREIGN KEY (`categorias_idcategorias`) REFERENCES `categorias` (`idcategorias`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_miscategorias_independiente1` FOREIGN KEY (`independiente_ndocumento`) REFERENCES `independiente` (`idindependiente`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `misfotos`
--
ALTER TABLE `misfotos`
  ADD CONSTRAINT `fk_misfotos_independiente1` FOREIGN KEY (`independiente_ndocumento`) REFERENCES `independiente` (`idindependiente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_misfotos_servicio1` FOREIGN KEY (`servicio_idservicio`) REFERENCES `servicio` (`idservicio`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `municipio`
--
ALTER TABLE `municipio`
  ADD CONSTRAINT `fk_municipio_estado_cuenta1` FOREIGN KEY (`estado_cuenta_idestado_cuenta`) REFERENCES `estado_cuenta` (`idestado_cuenta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_municipio_independiente1` FOREIGN KEY (`independiente_ndocumento`) REFERENCES `independiente` (`idindependiente`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `pais`
--
ALTER TABLE `pais`
  ADD CONSTRAINT `fk_pais_departamento1` FOREIGN KEY (`departamento_iddepartamento`) REFERENCES `departamento` (`iddepartamento`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `publicaciones`
--
ALTER TABLE `publicaciones`
  ADD CONSTRAINT `publicaciones_ibfk_1` FOREIGN KEY (`servicio_idservicio`) REFERENCES `servicio` (`idservicio`);

--
-- Filtros para la tabla `servicio`
--
ALTER TABLE `servicio`
  ADD CONSTRAINT `fk_servicio_categorias1` FOREIGN KEY (`categorias_idcategorias`) REFERENCES `categorias` (`idcategorias`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_servicio_independiente_independiente1` FOREIGN KEY (`independiente_ndocumento`) REFERENCES `independiente` (`idindependiente`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `subcategorias`
--
ALTER TABLE `subcategorias`
  ADD CONSTRAINT `fk_subcategorias_categorias1` FOREIGN KEY (`categorias_idcategorias`) REFERENCES `categorias` (`idcategorias`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_usuario_rol1` FOREIGN KEY (`rol_idrol`) REFERENCES `rol` (`idrol`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
