-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th1 03, 2024 lúc 03:43 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `forestcineplex`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `historyfoodorder`
--

CREATE TABLE `historyfoodorder` (
  `ID` int(11) NOT NULL,
  `Time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `User` varchar(30) NOT NULL,
  `Total` decimal(10,0) NOT NULL,
  `History` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `historyfoodorder`
--

INSERT INTO `historyfoodorder` (`ID`, `Time`, `User`, `Total`, `History`) VALUES
(6, '2024-01-03 14:04:55', 'admin', 9, 'Pop corn 60 OZ x 1; Pop corn caramel 60 OZ x 1; Coca 32 OZ x 1; Pepsi 32 OZ x 1; Sprite 32 OZ x 1; ');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `mycompany`
--

CREATE TABLE `mycompany` (
  `Budget` decimal(10,0) NOT NULL,
  `TicketSold` int(11) NOT NULL,
  `AvailableMovie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `mycompany`
--

INSERT INTO `mycompany` (`Budget`, `TicketSold`, `AvailableMovie`) VALUES
(148, 10, 10);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `User` varchar(30) NOT NULL,
  `PasswordHash` varchar(60) NOT NULL,
  `Role` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`User`, `PasswordHash`, `Role`) VALUES
('admin', '$2a$12$E1X/9BEyddoRdgty4WYIo.khLSgrjEKlFeJVS0PSB.WvX5wo/wvmW', 'Admin');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `historyfoodorder`
--
ALTER TABLE `historyfoodorder`
  ADD PRIMARY KEY (`ID`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`User`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `historyfoodorder`
--
ALTER TABLE `historyfoodorder`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
