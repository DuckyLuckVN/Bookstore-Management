CREATE DATABASE BookStore
GO

USE BookStore
GO

CREATE TABLE CATEGORY
(
	id VARCHAR(50) PRIMARY KEY,
	category_title NVARCHAR(50),
	category_description TEXT
)
GO

CREATE TABLE LOCATION
(
	id VARCHAR(50) PRIMARY KEY,
	location_name NVARCHAR(256) NOT NULL,
	max_storage INT CHECK(max_storage > 0),
	[description] NVARCHAR(256)
)

CREATE TABLE BOOK
(
	id VARCHAR(50) PRIMARY KEY,
	title NVARCHAR(100) NOT NULL,
	category_id varchar(50) NOT NULL,
	page_num INT,
	author NVARCHAR(50) NOT NULL,
	amount INT ,
	publisher NVARCHAR(50),
	publication_year INT,
	price MONEY,
	image NVARCHAR(256),
	location_id VARCHAR(50),
	description NVARCHAR(256),
	created_date date,
	CONSTRAINT fk_cateID FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_book_location_id FOREIGN KEY (location_id) REFERENCES dbo.LOCATION(id) ON UPDATE CASCADE
)
GO



CREATE TABLE [USER]
(
	id INT IDENTITY(100,1) PRIMARY KEY NOT NULL,
    username VARCHAR(100) UNIQUE NOT NULL,
	password VARCHAR(100) NOT NULL,
	fullname NVARCHAR(50),
	date_of_birth DATE,
	email VARCHAR(50),
	phone_number VARCHAR(14),
	sex BIT,
	created_date DATE
)
GO

CREATE TABLE [ADMIN]
(
	id INT IDENTITY(101,2) PRIMARY KEY NOT NULL,
	username VARCHAR(50) UNIQUE NOT NULL,
	password VARCHAR(50) NOT NULL,
	fullName NVARCHAR(256) NOT NULL,
	email VARCHAR(50) ,
	phone_number VARCHAR(14) NOT NULL,
	image NVARCHAR(256) NULL,
	sex BIT,
	role INT NOT NULL,
	isActive BIT,
	created_date DATE
)
GO

CREATE TABLE STORAGE
(
	id INT IDENTITY(100, 1) PRIMARY KEY,
	admin_id INT NOT NULL,
	description NVARCHAR(256) NULL,
	created_date DATE DEFAULT(GETDATE()),
	CONSTRAINT fk_storage_admin_id FOREIGN KEY (admin_id) REFERENCES dbo.ADMIN(id) ON DELETE CASCADE
	ON UPDATE CASCADE 
)
GO

CREATE TABLE STORAGE_DETAIL
(
	storage_id INT,
	book_id VARCHAR(50),
	amount INT CHECK (amount > 0),
	price MONEY,

	PRIMARY KEY (storage_id, book_id),
	CONSTRAINT fk_storage_detail_storeage_id FOREIGN KEY (storage_id) REFERENCES dbo.STORAGE(id) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_storage_detail_book_id FOREIGN KEY (book_id) REFERENCES dbo.BOOK(id) ON UPDATE CASCADE ON DELETE CASCADE
)
GO

CREATE TABLE RENTBOOK
(
	id INT PRIMARY KEY IDENTITY(100, 1),
	user_id INT,
	admin_id int, 
	created_date DATE,
	returned_date DATE,
	status INT,
	CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES [USER](id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_admin1 FOREIGN KEY (admin_id) REFERENCES Admin(id) ON DELETE CASCADE ON UPDATE CASCADE
)
GO 

CREATE TABLE RENTBOOK_DETAIL
(
	rentbook_id int ,
	book_id varchar(50),
	amount INT NOT NULL,
	price money,
	PRIMARY KEY (rentBook_id,book_id),
	CONSTRAINT fk_renbook FOREIGN KEY (rentbook_id) REFERENCES dbo.RENTBOOK(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_bookID FOREIGN KEY (book_id) REFERENCES Book(id) ON DELETE CASCADE ON UPDATE CASCADE
)
GO

CREATE TABLE [ORDER]
(
	id int PRIMARY KEY IDENTITY(100, 1),
	user_id INT NULL,
	admin_id int,
	date_created DATE 
	CONSTRAINT fk_Book FOREIGN KEY (user_id) REFERENCES [USER](id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_ADMIN_ORDER FOREIGN KEY (admin_id) REFERENCES dbo.ADMIN(id) ON DELETE CASCADE ON UPDATE CASCADE
)
GO

CREATE TABLE [ORDER_DETAIL]
(
	order_id INT NOT NULL,
	book_id VARCHAR(50) NOT NULL,
	amount INT NOT NULL,
	price MONEY NOT NULL,
	PRIMARY KEY(order_id,book_id),
	CONSTRAINT fk_order1_book FOREIGN KEY (order_id) REFERENCES [dbo].[ORDER](id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_book_oder FOREIGN KEY (book_id) REFERENCES dbo.BOOK(id) ON DELETE CASCADE ON UPDATE CASCADE
)
GO

CREATE TABLE BOOK_LOST
(
	rentbook_id INT PRIMARY KEY,
	admin_id INT,
	created_date DATE,
	CONSTRAINT fk_LostBook_RentBook_id FOREIGN KEY (rentbook_id) REFERENCES [dbo].RENTBOOK(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_LostBook_Admin_id FOREIGN KEY (admin_id) REFERENCES [dbo].[ADMIN](id)
)
GO

CREATE TABLE BOOK_LOST_DETAIL
(
	rentbook_id INT,
	book_id VARCHAR(50) NOT NULL,
	amount INT NOT NULL CHECK (amount > 0),
	cost MONEY,
	PRIMARY KEY (rentbook_id, book_id),
	CONSTRAINT fk_LostBook_Detail_RentBook_id FOREIGN KEY (rentbook_id) REFERENCES [dbo].BOOK_LOST(rentbook_id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_LostBook__Detail_Book_id FOREIGN KEY (book_id) REFERENCES dbo.BOOK(id) ON DELETE CASCADE ON UPDATE CASCADE
)
GO

INSERT dbo.LOCATION ( id, location_name, max_storage, description)
VALUES  ( 'A1', N'Kệ A1', 100, N''),
		( 'A2', N'Kệ A2', 120, N''),
		( 'A3', N'Kệ A3', 130, N''),
		( 'A4', N'Kệ A4', 90, N''),
		( 'A5', N'Kệ A5', 100, N''),
		( 'A6', N'Kệ A6', 100, N''),
		( 'A7', N'Kệ A7', 120, N''),
		( 'A8', N'Kệ A8', 110, N'')

INSERT INTO dbo.CATEGORY( id ,category_title ,category_description)
VALUES  ('TT2'	 ,N'Kỉ Niệm HÀ NỘI tôi' ,'71 trang'),
		('PT5',N'Đi tìm ngày xưa',''),
		('AG1',N'Sống đúng','')
GO

INSERT INTO dbo.BOOK (id ,title ,category_id ,page_num ,author ,amount ,publisher ,publication_year ,price ,image, location_id ,description ,created_date)
VALUES  ('GH12' ,N'TÔI THẤY MÌNH CÒN TRẺ','TT2' ,274 ,N'Đỗ Văn Hoàn' ,96 ,N'NXB Trẻ' ,2017 ,296999 , N'', 'A1' ,'' ,'11/05/2018' ),  
		('JH42' ,N'TÔI THẤY HOA VÀNG TRÊN CỎ XANH','AG1' ,274 ,N'Lê Văn Thuyết' ,96 ,N'NXB Nhi Đồng' ,2017 ,196333 , N'', 'A2' ,'' ,'11/06/2018' )
GO
	
INSERT INTO dbo.[USER]( username ,password ,fullname ,date_of_birth ,email ,phone_number)
VALUES  ( 'haond' , '123' , N'Nguyễn Văn Hao' , '1999-06-11', 'teonv@gmail.com' , '0623457413'),
		( 'nopt12' , '123' , N'Nguyễn Thị Nở' , '1993-03-11', 'nopt@gmail.com' , '054632179')
GO

INSERT INTO dbo.ADMIN( username ,password ,fullName , email ,phone_number ,role ,created_date)
VALUES  ( 'quanly' , '123' , N'Lý Tiểu Long' , 'lytieulong@gmail.com' ,'01682439314' , 1 ,'07/05/2015'),
		('truongphong' , '123' , N'Nguyễn Đại Trân' , 'ngueyndairan@gmail.com' ,'0123456789' , 1 ,'06/05/2012')
GO


INSERT INTO dbo.[ORDER](user_id ,admin_id ,date_created)
VALUES  ( 100 , 101 , GETDATE() ),
		( 101 , 103 , GETDATE())
GO

INSERT INTO ORDER_DETAIL(order_id ,	book_id, amount, price)
VALUES	(102,'GH12',3,200000),
		(103,'JH42', 2, 300000)
GO

INSERT INTO dbo.RENTBOOK(user_id , admin_id ,created_date ,status)
VALUES  (100, 101, GETDATE() , 0),
		(101, 103, GETDATE() , 1)
GO

INSERT INTO RENTBOOK_DETAIL (rentbook_id , book_id , amount, price)
VALUES	(102,'GH12',3,300000),
		(103,'JH42',4,400000),
		(104,'GH12',2,400000)
GO

INSERT INTO BOOK_LOST(rentbook_id,admin_id,created_date)
VALUES (102,101,GETDATE()),
		(103,103,GETDATE())
GO

INSERT INTO BOOK_LOST_DETAIL(rentbook_id,book_id,amount,cost)
VALUES (102,'GH12',2, 10000),
		(103,'JH42',2, 10000)
GO

INSERT INTO dbo.STORAGE( admin_id, description, created_date)
VALUES  ( 101, N'Không ghi chú gì hết', GETDATE())
GO

INSERT INTO dbo.STORAGE_DETAIL ( storage_id, book_id, amount, price )
VALUES  ( 100, 'GH12', 90, 250000),
		( 100, 'JH42', 50, 180000)

SELECT * FROM dbo.BOOK
SELECT * FROM dbo.ORDER_DETAIL
SELECT * FROM [ADMIN]
GO


/****** Object:  StoredProcedure  [sp_getOrderDetail]  Script Date: 7/17/2019 ******/
--Lấy ra thông tin mã sách, số lượng và giá sách dựa vào thông tin order truyền vào
CREATE PROC sp_getOrderDetail(@order_id INT)
AS BEGIN
	SELECT
		Book.ID AS book_id,
		ORDER_DETAIL.amount AS amount, 
		ORDER_DETAIL.price AS price
	FROM dbo.BOOK 
		JOIN ORDER_DETAIL ON Book.ID = ORDER_DETAIL.book_id
	WHERE 
		ORDER_DETAIL.order_id = @order_id
END


/****** Object:  StoredProcedure  [sp_getCountBook]  Script Date: 7/17/2019 ******/
--Lấy về số lượng sách còn trong kho của sách có mã là @book_id
GO
CREATE PROC sp_getCountBook(@book_id VARCHAR(50))
AS BEGIN
	SELECT
		amount AS amount
	FROM Book
	WHERE BOOK.id = @book_id
END
GO
/****** Object:  StoredProcedure  [sp_getCountBookSold]  Script Date: 7/17/2019 ******/
--Lấy về số lượng sách đã bán của sách có id là @book_id
CREATE PROC sp_getCountBookSold(@book_id VARCHAR(50))
AS BEGIN
	SELECT
		od.amount amount_sold
	FROM Book b
	join ORDER_DETAIL od ON b.ID = od.book_id
	WHERE b.id = @book_id
END
GO

/****** Object:  StoredProcedure  [sp_getCountBookBeingRented]  Script Date: 7/17/2019 ******/
--Lấy về số lượng sách "ĐANG" được thuê của sách có id là @book_id
GO
CREATE PROC sp_getCountBookBeingRented(@book_id VARCHAR(50))
AS BEGIN
	SELECT
		SUM(rd.amount)
	FROM RENTBOOK_DETAIL rd
		JOIN Book b ON b.ID = rd.book_id
		JOIN RENTBOOK r ON r.ID = rd.rentbook_id 
	WHERE r.status = 0 AND rd.book_id = @book_id
END
GO


SELECT * FROM dbo.RENTBOOK_DETAIL
EXEC dbo.sp_getCountBookBeingRented @book_id = 'GH12' -- varchar(50)

/****** Object:  StoredProcedure  [sp_getRentBookDetail]  Script Date: 7/17/2019 ******/
--Từ id rentbook truyền vào, lấy ra các thông tin chi tiết của hóa đơn thuê sách
GO
CREATE PROC sp_getRentBookDetail(@rentbook_id INT)
AS BEGIN
	SELECT
		BOOK.id book_id, 
		RENTBOOK_DETAIL.amount amount, 
		RENTBOOK_DETAIL.price price
	FROM Book
		JOIN RENTBOOK_DETAIL ON Book.ID = RENTBOOK_DETAIL.book_id
	WHERE RENTBOOK_DETAIL.rentbook_id = @rentbook_id
END
GO
/****** Object:  StoredProcedure  [sp_getBookSoldByMonth]  Script Date: 7/17/2019 ******/
--Trả về thông tin thống kê sách đã bán được theo tháng
GO
DROP PROC sp_getBookSoldByMonth

CREATE PROC sp_getBookSoldByMonth(@month INT)
AS BEGIN
	SELECT
		COUNT(ORDER_DETAIL.amount) amount_sold,
		Book.ID book_id
	FROM ORDER_DETAIL
		join Book ON Book.ID = ORDER_DETAIL.book_id
		join [ORDER] ON [ORDER].ID = ORDER_DETAIL.order_id
	WHERE MONTH([ORDER].Date_created) = @month AND YEAR([ORDER].Date_created) = YEAR(GETDATE())
	GROUP BY Book.ID 
END

SELECT * FROM BOOK
EXEC dbo.sp_getBookSoldByMonth @month = 8 -- int


/****** Object:  StoredProcedure  [sp_getIncomeByMonth]  Script Date: 7/19/2019 ******/
--Trả về thông tin thống kê tiền sách thu được khi bán sách
GO
CREATE PROC sp_getIncomeByMonth(@month INT)
AS BEGIN
	SELECT 
		BOOK.id MaSach,
		SUM(ORDER_DETAIL.amount) amount_sold,
		SUM( ORDER_DETAIL.price * ORDER_DETAIL.amount) money_total
	FROM ORDER_DETAIL
		join BOOK ON ORDER_DETAIL.book_id = BOOK.id
		join [ORDER] ON ORDER_DETAIL.order_id = [ORDER].id
		WHERE MONTH([ORDER].Date_created) = @month
	GROUP BY BOOK.id, ORDER_DETAIL.price, ORDER_DETAIL.amount
END
GO

/****** Object:  StoredProcedure  [sp_getTotalRentbook]  Script Date: 8/03/2019 ******/
--Trả về tổng số sách đã thuê trong RENTBOOK_DETAIL theo renbook_id
CREATE PROC sp_getTotalRentBook (@rentbook_id INT)
AS BEGIN
	SELECT
		SUM(amount)
	FROM RENTBOOK_DETAIL
	WHERE rentbook_id = @rentbook_id
END
GO

/****** Object:  StoredProcedure  [sp_getTotalCostBookLost]  Script Date: 8/03/2019 ******/
--Trả về tổng số cost có trong LostBook theo renbook_id
CREATE PROC sp_getTotalCostBookLost (@rentbook_id INT)
AS BEGIN
	SELECT
		SUM(cost)
	FROM dbo.BOOK_LOST_DETAIL
	WHERE rentbook_id = @rentbook_id
END				
GO

/****** Object:  StoredProcedure  [sp_getTotalCountBookLost]  Script Date: 8/03/2019 ******/
--Trả về tổng số sách đã mất theo renbook_id
GO
CREATE PROC sp_getTotalCountBookLost (@rentbook_id INT)
AS BEGIN
	SELECT
		SUM(amount)
	FROM dbo.BOOK_LOST_DETAIL
	WHERE rentbook_id = @rentbook_id
END
GO

/****** Object:  StoredProcedure  [sp_getAmountBookRented]  Script Date: 8/03/2019 ******/
--Trả về tổng số sách của sách có @book_id đã thuê với đơn thuê có mã @rentbook_id
GO
CREATE PROC sp_getAmountBookRented (@rentbook_id INT, @book_id VARCHAR(50))
AS BEGIN
	SELECT
		SUM(amount)
	FROM RENTBOOK_DETAIL
	WHERE rentbook_id = @rentbook_id AND book_id = @book_id
END
GO


/****** Object:  StoredProcedure  [sp_getLostBookDetail]  Script Date: 8/03/2019 ******/
--Trả về thông tin chi tiết của LOST_BOOK_DETAIL theo rentbook_id
CREATE PROC sp_getLostBookDetail (@rentbook_id INT)
AS BEGIN
	SELECT
		book_id,
		amount,
		cost
	FROM dbo.BOOK_LOST_DETAIL
	WHERE rentbook_id = @rentbook_id
END
GO

/****** Object:  StoredProcedure  [sp_getLostBookDetail]  Script Date: 8/04/2019 ******/
--Trả về tổng số sách đã nhập trong hóa đơn nhập
CREATE PROC sp_getTotalCountBookOfStorage (@storage_id INT)
AS BEGIN
	SELECT
		SUM(amount)
	FROM STORAGE_DETAIL
	WHERE storage_id = @storage_id
END
GO

/****** Object:  StoredProcedure  [sp_getSumCountOrderSold]  Script Date: 8/07/2019 ******/
--Trả về tổng số lượng sách đã bán
CREATE PROC sp_getCountBookInOrder (@order_id INT)
AS BEGIN
	SELECT
		SUM(amount)
	FROM ORDER_DETAIL
	WHERE order_id = @order_id
END
GO

/****** Object:  StoredProcedure  [sp_getSumPriceOrderSold]  Script Date: 8/07/2019 ******/
--Trả về tổng số lượng sách đã bán
CREATE PROC sp_getTotalPriceInOrder(@order_id INT)
AS BEGIN
	SELECT
		SUM(price * amount)
	FROM ORDER_DETAIL
	WHERE order_id = @order_id
END
GO
	

SELECT * FROM dbo.BOOK
SELECT * FROM dbo.LOCATION

UPDATE dbo.BOOK SET description = 'abcdef descrip' WHERE id = 'GH12'

SELECT * FROM dbo.[USER]
SELECT * FROM dbo.ADMIN
SELECT * FROM dbo.[ORDER]
SELECT * FROM dbo.RENTBOOK
SELECT * FROM dbo.RENTBOOK_DETAIL

DELETE FROM dbo.RENTBOOK
/****** Object:  StoredProcedure  [sp_getTotalRentbook]  Script Date: 8/03/2019 ******/
--Trả về tổng số sách đã thuê trong RENTBOOK_DETAIL theo renbook_id
GO
CREATE PROC sp_getTotalRentBook (@rentbook_id INT)
AS BEGIN
	SELECT
		SUM(amount)
	FROM RENTBOOK_DETAIL
	WHERE rentbook_id = @rentbook_id
END
/****** Object:  StoredProcedure  [sp_getTotalCostBookLost]  Script Date: 8/03/2019 ******/
--Trả về tổng số cost có trong LostBook theo renbook_id
GO
CREATE PROC sp_getTotalCostBookLost (@rentbook_id INT)
AS BEGIN
	SELECT
		SUM(cost)
	FROM BOOK_LOST
	WHERE rentbook_id = @rentbook_id
END
/****** Object:  StoredProcedure  [sp_getTotalCountBookLost]  Script Date: 8/03/2019 ******/
--Trả về tổng số sách đã mất theo renbook_id
GO
CREATE PROC sp_getTotalCountBookLost (@rentbook_id INT)
AS BEGIN
	SELECT
		SUM(amount)
	FROM BOOK_LOST
	WHERE rentbook_id = @rentbook_id
END
/****** Object:  StoredProcedure  [sp_getAmountBookRented]  Script Date: 8/03/2019 ******/
--Trả về tổng số sách của sách có @book_id đã thuê với đơn thuê có mã @rentbook_id
GO
CREATE PROC sp_getAmountBookRented (@rentbook_id INT, @book_id VARCHAR(50))
AS BEGIN
	SELECT
		SUM(amount)
	FROM RENTBOOK_DETAIL
	WHERE rentbook_id = @rentbook_id AND book_id = @book_id
END
/****** Object:  StoredProcedure  [sp_getLostBookDetail]  Script Date: 8/03/2019 ******/
--Trả về thông tin chi tiết của LOST_BOOK_DETAIL theo rentbook_id
GO
CREATE PROC sp_getLostBookDetail (@rentbook_id INT)
AS BEGIN
	SELECT
		book_id,
		amount,
		cost
	FROM BOOK_LOST
	WHERE rentbook_id = @rentbook_id
END 
/****** Object:  StoredProcedure  [sp_getLostBookDetail]  Script Date: 8/04/2019 ******/
--Trả về tổng số sách đã nhập trong hóa đơn nhập
GO
CREATE PROC sp_getTotalCountBookOfStorage (@storage_id INT)
AS BEGIN
	SELECT
		SUM(amount)
	FROM STORAGE_DETAIL
	WHERE storage_id = @storage_id
END
/****** Object:  StoredProcedure  [sp_getSumCountOrderSold]  Script Date: 8/07/2019 ******/
--Trả về tổng số lượng sách đã bán
GO
CREATE PROC sp_getSumCountOrderSold (@order_id INT)
AS BEGIN
	SELECT
		SUM(amount)
	FROM ORDER_DETAIL
	WHERE order_id = @order_id
END
/****** Object:  StoredProcedure  [sp_getSumPriceOrderSold]  Script Date: 8/07/2019 ******/
--Trả về tổng số lượng sách đã bán
GO
CREATE PROC sp_getSumPriceOrderSold (@order_id INT)
AS BEGIN
	SELECT
		SUM(price)
	FROM ORDER_DETAIL
	WHERE order_id = @order_id
END
GO
/****** Object:  StoredProcedure  [sp_getAmountAvailableLocation]  Script Date: 8/08/2019 ******/
--Trả về số lượng sức chứa còn lại của location
CREATE PROC sp_getAmountAvailableLocation (@location_id VARCHAR(50))
AS BEGIN
	SELECT
		(LOCATION.max_storage -
		(SELECT SUM(amount) FROM BOOK WHERE location_id = @location_id))
	FROM BOOK
	JOIN LOCATION ON BOOK.location_id = LOCATION.id
	WHERE LOCATION.id = @location_id
	GROUP BY LOCATION.max_storage
END
GO
/****** Object:  StoredProcedure  [sp_getStatisticOverviewInMonth]  Script Date: 8/10/2019 ******/
--Trả về tổng tất cả các thứ cần thống ke :))
DROP PROC sp_getStatisticOverviewInMonth
GO
CREATE PROC sp_getStatisticOverviewInMonth 
AS BEGIN
	DECLARE @totalOrder FLOAT = 0
	DECLARE @totalRent FLOAT = 0
	DECLARE @totalLost FLOAT = 0
	DECLARE @totalUser FLOAT = 0
	DECLARE @totalAddStorage FLOAT = 0
	DECLARE @totalRevenue FLOAT = 0

	SELECT @totalOrder = SUM(ORDER_DETAIL.amount) FROM ORDER_DETAIL, [ORDER] 
	WHERE ORDER_DETAIL.order_id = [ORDER].id 
	AND YEAR([ORDER].date_created) = YEAR(GETDATE()) 
	AND MONTH([ORDER].date_created) = MONTH(GETDATE())
	
	SELECT @totalRent = SUM(RENTBOOK_DETAIL.amount) FROM RENTBOOK_DETAIL, RENTBOOK
	WHERE  RENTBOOK_DETAIL.rentbook_id = RENTBOOK.id
	AND YEAR(RENTBOOK.created_date) = YEAR(GETDATE()) 
	AND MONTH(RENTBOOK.created_date) = MONTH(GETDATE()) 
	
	SELECT @totalLost = SUM(BOOK_LOST_DETAIL.amount) FROM  BOOK_LOST_DETAIL, BOOK_LOST
	WHERE BOOK_LOST_DETAIL.rentbook_id = BOOK_LOST.rentbook_id 
	AND YEAR(BOOK_LOST.created_date) = YEAR(GETDATE()) 
	AND MONTH(BOOK_LOST.created_date) = MONTH(GETDATE())
	
	SELECT @totalUser = COUNT([USER].id) FROM [USER]
	WHERE YEAR([USER].created_date) = YEAR(GETDATE()) 
	AND MONTH([USER].created_date) = MONTH(GETDATE())
	
	SELECT @totalAddStorage = SUM(STORAGE_DETAIL.amount) FROM STORAGE_DETAIL, STORAGE
	WHERE STORAGE_DETAIL.storage_id = STORAGE.id
	AND YEAR(STORAGE.created_date) = YEAR(GETDATE()) 
	AND MONTH(STORAGE.created_date) = MONTH(GETDATE())
	
	DECLARE @totalSumOrder FLOAT = 0
	DECLARE @totalSumLost FLOAT = 0
	DECLARE @totalSumRentLost FLOAT = 0
	DECLARE @totalSumMoneyStorage FLOAT = 0

	SELECT @totalSumOrder = SUM(ORDER_DETAIL.amount*ORDER_DETAIL.price)
	FROM ORDER_DETAIL, [ORDER]
	WHERE ORDER_DETAIL.order_id = [ORDER].id
	AND YEAR([ORDER].date_created) = YEAR(GETDATE()) 
	AND MONTH([ORDER].date_created) = MONTH(GETDATE())

	SELECT @totalSumLost = SUM(BOOK_LOST_DETAIL.cost)
	FROM BOOK_LOST, BOOK_LOST_DETAIL
	WHERE BOOK_LOST_DETAIL.rentbook_id = BOOK_LOST.rentbook_id
	AND YEAR(BOOK_LOST.created_date) = YEAR(GETDATE()) 
	AND MONTH(BOOK_LOST.created_date) = MONTH(GETDATE())

	SELECT @totalSumRentLost = SUM(BOOK_LOST_DETAIL.amount*RENTBOOK_DETAIL.price)
	FROM BOOK_LOST, BOOK_LOST_DETAIL, RENTBOOK_DETAIL,RENTBOOK
	WHERE BOOK_LOST.rentbook_id = RENTBOOK.id
	AND RENTBOOK.id = RENTBOOK_DETAIL.rentbook_id
	AND RENTBOOK_DETAIL.book_id = BOOK_LOST_DETAIL.book_id
	AND RENTBOOK_DETAIL.rentbook_id = BOOK_LOST_DETAIL.rentbook_id
	AND YEAR(RENTBOOK.created_date) = YEAR(GETDATE()) 
	AND MONTH(RENTBOOK.created_date) = MONTH(GETDATE())
	AND YEAR(BOOK_LOST.created_date) = YEAR(GETDATE()) 
	AND MONTH(BOOK_LOST.created_date) = MONTH(GETDATE())

	SELECT @totalSumMoneyStorage = SUM(STORAGE_DETAIL.amount*STORAGE_DETAIL.price)
	FROM STORAGE, STORAGE_DETAIL
	WHERE STORAGE.id = STORAGE_DETAIL.storage_id
	AND YEAR(STORAGE.created_date) = YEAR(GETDATE())
	AND MONTH(STORAGE.created_date) = MONTH(GETDATE())

	IF (@totalLost IS NULL)
		SET @totalLost = 0
	IF (@totalSumOrder IS NULL)
		SET @totalSumOrder = 0
	IF (@totalSumRentLost IS NULL)
		SET @totalSumRentLost = 0
	IF (@totalSumLost IS NULL)
		SET @totalSumLost = 0
	IF (@totalSumMoneyStorage IS NULL)
		SET @totalSumMoneyStorage = 0

	SET @totalrevenue = @totalSumLost + @totalSumOrder - @totalSumRentLost - @totalSumMoneyStorage

	SELECT @totalOrder AS [Total Order], @totalRent AS [Total Rent], @totalLost AS [Total Lost], @totalUser AS [Total User], @totalAddStorage AS [Total Book Storage], @totalRevenue AS [Total imcome]
END
GO

select * from [USER]
select * from BOOK_LOST
select * from [ORDER]
select * from ORDER_DETAIL
select * from RENTBOOK
select * from RENTBOOK_DETAIL
select * from BOOK_LOST_DETAIL

EXEC sp_getStatisticOverviewInMonth
