-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-10-2022 a las 16:38:21
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `restaurante`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas`
--

create database restaurante;
use restaurante;


CREATE TABLE `facturas` (
  `id` int(11) NOT NULL,
  `monto` float NOT NULL,
  `menu_id` int(11) NOT NULL,
  `usuarios_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `menu`
--

CREATE TABLE `menu` (
  `menu_id` int(11) NOT NULL,
  `producto` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `precio` double NOT NULL,
  `stock` int(11) NOT NULL,
  `tipo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `menu`
--

INSERT INTO `menu` (`menu_id`, `producto`, `precio`, `stock`, `tipo`) VALUES
(1, 'napolitana', 1550, 10, 'carnes'),
(2, 'ojo de bife', 1550, 15, 'carnes'),
(3, 'rabas', 1700, 30, 'carnes'),
(4, 'suprema', 1650, 20, 'carnes'),
(5, 'tira de asado', 1950, 8, 'carnes'),
(6, 'fetuccini', 1250, 12, 'pastas'),
(7, 'noquis', 1200, 10, 'pastas'),
(8, 'ravioles', 1550, 15, 'pastas'),
(9, 'sorrentinos', 1500, 20, 'pastas'),
(10, 'tallarines', 1300, 17, 'pastas'),
(11, 'calabresa', 1750, 21, 'pizzas'),
(12, 'fugazzeta', 1850, 7, 'pizzas'),
(13, 'muzzarella', 1600, 30, 'pizzas'),
(14, 'napolitana', 1750, 13, 'pizzas'),
(15, 'palmitos', 1600, 25, 'pizzas'),
(16, 'banana split', 850, 22, 'postres'),
(17, 'budin', 350, 32, 'postres'),
(18, 'duraznos con crema', 700, 20, 'postres'),
(19, 'flan', 250, 40, 'postres'),
(20, 'empanadas', 1100, 15, 'promos'),
(21, 'fritas cheddar', 750, 25, 'promos'),
(22, 'supernapo', 3550, 8, 'promos'),
(23, 'gaseosa', 250, 40, 'bebidas'),
(24, 'agua gasificada', 200, 35, 'bebidas'),
(25, 'malbec', 850, 23, 'bebidas'),
(26, 'patagona', 350, 32, 'bebidas'),
(27, 'ravioles', 1550, 10, 'menu del dia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mesas`
--

CREATE TABLE `mesas` (
  `id` int(11) NOT NULL,
  `numero` int(11) NOT NULL,
  `reservado` varchar(50) NOT NULL
) ;

--
-- Volcado de datos para la tabla `mesas`
--

INSERT INTO `mesas` (`id`, `numero`, `reservado`) VALUES
(1, 1, 'no'),
(2, 2, 'no'),
(3, 3, 'no'),
(4, 4, 'no'),
(5, 5, 'no'),
(6, 6, 'no'),
(7, 7, 'no'),
(8, 8, 'no'),
(9, 9, 'no');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `promociones`
--

CREATE TABLE `promociones` (
  `id` int(11) NOT NULL,
  `descuento` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `promociones`
--

INSERT INTO `promociones` (`id`, `descuento`, `menu_id`) VALUES
(1, 25, 4),
(2, 15, 9),
(3, 25, 13);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

CREATE TABLE `reservas` (
  `id` int(11) NOT NULL,
  `usuarios_id` int(11) NOT NULL,
  `mesas_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nombre` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `user` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `contraseña` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `correo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `user`, `contraseña`, `correo`) VALUES
(2, 'Damian', 'dami', '1234', 'correo@correo.com');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD PRIMARY KEY (`id`,`menu_id`,`usuarios_id`),
  ADD KEY `fk_facturas_menu1_idx` (`menu_id`),
  ADD KEY `fk_facturas_usuarios1_idx` (`usuarios_id`);

--
-- Indices de la tabla `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`menu_id`);

--
-- Indices de la tabla `mesas`
--
ALTER TABLE `mesas`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indices de la tabla `promociones`
--
ALTER TABLE `promociones`
  ADD PRIMARY KEY (`id`,`menu_id`),
  ADD KEY `fk_promociones_menu1_idx` (`menu_id`);

--
-- Indices de la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`id`,`usuarios_id`,`mesas_id`) USING BTREE,
  ADD KEY `fk_reservas_usuarios_idx` (`usuarios_id`),
  ADD KEY `fk_reservas_mesas1_idx` (`mesas_id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `facturas`
--
ALTER TABLE `facturas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `menu`
--
ALTER TABLE `menu`
  MODIFY `menu_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT de la tabla `mesas`
--
ALTER TABLE `mesas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `promociones`
--
ALTER TABLE `promociones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `reservas`
--
ALTER TABLE `reservas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD CONSTRAINT `fk_facturas_menu1` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`),
  ADD CONSTRAINT `fk_facturas_usuarios1` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `promociones`
--
ALTER TABLE `promociones`
  ADD CONSTRAINT `fk_promociones_menu1` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`);

--
-- Filtros para la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD CONSTRAINT `fk_reservas_usuarios` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`mesas_id`) REFERENCES `mesas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
