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
GO

CREATE TABLE AUTHOR
(
	id INT IDENTITY (100, 1) PRIMARY KEY,
	fullname NVARCHAR(256) NOT NULL,
	date_of_birth DATE,
	date_of_death DATE NULL,
	image NVARCHAR(256),
	introduce NVARCHAR(256),
	created_date DATE,
)
GO

CREATE TABLE PUBLISHER
(
	id INT IDENTITY (100, 1) PRIMARY KEY,
	name NVARCHAR(256) NOT NULL,
	phone_number VARCHAR(13),
	email VARCHAR(100) NOT NULL,
	address NVARCHAR(200),
	introduce NVARCHAR(256),
	created_date DATE
)
GO

CREATE TABLE BOOK
(
	id VARCHAR(50) PRIMARY KEY,
	title NVARCHAR(100) NOT NULL,
	category_id varchar(50) NOT NULL,
	page_num INT,
	author_id INT,
	amount INT ,
	publisher_id INT,
	publication_year INT,
	price MONEY,
	image NVARCHAR(256),
	location_id VARCHAR(50),
	description NVARCHAR(256),
	introduce NVARCHAR(256),
	created_date DATE,
    
	CONSTRAINT FK_Book_category_id FOREIGN KEY (category_id) REFERENCES dbo.CATEGORY(id) ON UPDATE CASCADE,
	CONSTRAINT FK_Book_Location_id FOREIGN KEY (location_id) REFERENCES dbo.LOCATION(id) ON UPDATE CASCADE,
	CONSTRAINT FK_Book_Author_id FOREIGN KEY (author_id) REFERENCES dbo.AUTHOR(id) ON UPDATE CASCADE,
	CONSTRAINT FK_Book_publisher_id FOREIGN KEY (publisher_id) REFERENCES dbo.PUBLISHER(id) ON UPDATE CASCADE
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
	isActive BIT,
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

	CONSTRAINT fk_storage_admin_id FOREIGN KEY (admin_id) REFERENCES dbo.ADMIN(id) ON UPDATE CASCADE
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
	CONSTRAINT fk_storage_detail_book_id FOREIGN KEY (book_id) REFERENCES dbo.BOOK(id) ON UPDATE CASCADE
)
GO

CREATE TABLE RENTBOOK
(
	id INT PRIMARY KEY IDENTITY(100, 1),
	user_id INT,
	admin_id int, 
	cost_rent MONEY,
	cost_expiration MONEY,
	expiration_day SMALLINT,
	created_date DATE,
	returned_date DATE,
	status INT,

	CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES [USER](id) ON UPDATE CASCADE,
	CONSTRAINT fk_admin1 FOREIGN KEY (admin_id) REFERENCES Admin(id) ON UPDATE CASCADE
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
	CONSTRAINT fk_bookID FOREIGN KEY (book_id) REFERENCES Book(id) ON UPDATE CASCADE
)
GO

CREATE TABLE [ORDER]
(
	id int PRIMARY KEY IDENTITY(100, 1),
	user_id INT NULL,
	admin_id int,
	date_created DATE ,
	CONSTRAINT fk_Book FOREIGN KEY (user_id) REFERENCES [USER](id) ON UPDATE CASCADE,
	CONSTRAINT fk_ADMIN_ORDER FOREIGN KEY (admin_id) REFERENCES dbo.ADMIN(id) ON UPDATE CASCADE
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
	CONSTRAINT fk_book_oder FOREIGN KEY (book_id) REFERENCES dbo.BOOK(id) ON UPDATE CASCADE,
)
GO

CREATE TABLE BOOK_LOST
(
	rentbook_id INT PRIMARY KEY,
	admin_id INT,
	cost_lost SMALLMONEY,
	created_date DATE,
	CONSTRAINT fk_LostBook_RentBook_id FOREIGN KEY (rentbook_id) REFERENCES [dbo].RENTBOOK(id) ON UPDATE CASCADE,
	CONSTRAINT fk_LostBook_Admin_id FOREIGN KEY (admin_id) REFERENCES [dbo].[ADMIN](id),
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
	CONSTRAINT fk_LostBook__Detail_Book_id FOREIGN KEY (book_id) REFERENCES dbo.BOOK(id) ON UPDATE CASCADE
)
GO

INSERT dbo.LOCATION ( id, location_name, max_storage, description)
VALUES  ( 'A1', N'Kệ A1', 100, N''),
		( 'A4', N'Kệ A4', 90, N''),
		( 'A2', N'Kệ A2', 120, N''),
		( 'A3', N'Kệ A3', 130, N''),
		( 'A5', N'Kệ A5', 100, N''),
		( 'A6', N'Kệ A6', 100, N''),
		( 'A7', N'Kệ A7', 120, N''),
		( 'A8', N'Kệ A8', 110, N'')
GO

INSERT INTO dbo.CATEGORY( id ,category_title ,category_description)
VALUES  ('TT2'	 ,N'Kỉ Niệm HÀ NỘI tôi' ,'71 trang'),
		('PT5',N'Đi tìm ngày xưa',''),
		('AG1',N'Sống đúng','')
GO

INSERT INTO dbo.AUTHOR(fullname ,date_of_birth ,image ,introduce ,created_date)
VALUES  (N'Đỗ Văn Hoàng', GETDATE(), N'', N'Tác giả này tên là Đỗ Văn Hoàng.', GETDATE()),
		(N'Trần Đăng Khoa', GETDATE(), N'', N'Tác giả này nổi tiếng với các bài văn dành cho trẻ em', GETDATE()),
		(N'Lê Văn Thuyết', GETDATE(), N'', N'', GETDATE())
GO

INSERT INTO dbo.PUBLISHER(name, phone_number, email, introduce, created_date)
VALUES  (N'BXB Trẻ', '0376546521', 'contact@nxbtre.com', N'Đây là nhà xuất bản khá trẻ :v', GETDATE()),
		(N'BXB Nhi Đồng', '0186224665', 'contact@nxbnhidong.com', N'Đây là nhà xuất bản nhi đồng', GETDATE())
GO

INSERT INTO dbo.BOOK (id ,title ,category_id ,page_num ,author_id ,amount ,publisher_id ,publication_year ,price ,image, location_id ,description ,created_date)
VALUES  ('GH12' ,N'TÔI THẤY MÌNH CÒN TRẺ','TT2' ,274 , 100 ,96 ,100 ,2017 ,296999 , N'', 'A1' ,'' ,'11/05/2018' ),  
		('JH42' ,N'TÔI THẤY HOA VÀNG TRÊN CỎ XANH','AG1' ,274 ,102 ,96 , 101 ,2017 ,196333 , N'', 'A2' ,'' ,'11/06/2018' )
GO
	
INSERT INTO dbo.[USER]( username ,password ,fullname ,date_of_birth ,email ,phone_number, isActive)
VALUES  ( 'haond' , '123' , N'Nguyễn Văn Hao' , '1999-06-11', 'teonv@gmail.com' , '0623457413', 1),
		( 'nopt12' , '123' , N'Nguyễn Thị Nở' , '1993-03-11', 'nopt@gmail.com' , '054632179', 0)
GO

INSERT INTO dbo.ADMIN( username ,password ,fullName , email ,phone_number ,role ,created_date, isActive)
VALUES  ( 'quanly' , '123' , N'Lý Tiểu Long' , 'lytieulong@gmail.com' ,'01682439314' , 1 ,'07/05/2015', 0),
		('truongphong' , '123' , N'Nguyễn Đại Trân' , 'ngueyndairan@gmail.com' ,'0123456789' , 1 ,'06/05/2012', 1)
GO

INSERT INTO dbo.[ORDER](user_id ,admin_id, date_created)
VALUES  ( 100 , 101, GETDATE()),
		( 101 , 103, GETDATE())
GO

INSERT INTO ORDER_DETAIL(order_id ,	book_id, amount, price)
VALUES	(100,'GH12',3,200000),
		(101,'JH42', 2, 300000)
GO

INSERT INTO dbo.RENTBOOK(user_id , admin_id, cost_rent, cost_expiration, expiration_day, created_date, returned_date, status)
VALUES  (100, 101, 5000, 1000, 7, GETDATE() , NULL, 0),
		(101, 103, 5000, 1000, 7, GETDATE() , GETDATE(), 1)
GO

INSERT INTO RENTBOOK_DETAIL (rentbook_id , book_id , amount, price)
VALUES	(100,'GH12',3,300000),
		(101,'JH42',4,400000),
		(101,'GH12',2,400000)
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
CREATE PROC sp_getBookSoldByMonth(@month INT)
AS BEGIN
	SELECT
		Book.ID book_id, 
		COUNT(ORDER_DETAIL.amount) amount_sold
	FROM ORDER_DETAIL
		join Book ON Book.ID = ORDER_DETAIL.book_id
		join [ORDER] ON [ORDER].ID = ORDER_DETAIL.order_id
	WHERE MONTH([ORDER].Date_created) = @month
	GROUP BY Book.ID 
END

EXEC dbo.sp_getBookSoldByMonth @month = 7 -- int


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

/****** Object:  StoredProcedure  [sp_getTotalAmountOrderBook]  Script Date: 8/10/2019 ******/
--Trả về tổng số sách đã bán
CREATE PROC sp_getTotalAmountOrderBook 
AS BEGIN
	SELECT
		SUM(amount)
	FROM ORDER_DETAIL
END
GO

/****** Object:  StoredProcedure  [sp_getClosestPriceStorageWithBook]  Script Date: 8/13/2019 ******/
--Trả về giá tiền nhập sách của sách có id truyền vào tại thời điểm gần nhất
CREATE PROC sp_getClosestPriceStorageWithBook (@bookId VARCHAR(50))
AS BEGIN
	SELECT TOP 1 sdt.price FROM dbo.STORAGE s, dbo.STORAGE_DETAIL sdt
	WHERE s.id = sdt.storage_id AND sdt.book_id = @bookId
	ORDER BY s.created_date DESC
END
GO

/****** Object:  StoredProcedure  [sp_getStatisticOverviewInMonth]  Script Date: 8/10/2019 ******/
--Trả về tổng tất cả các thứ cần thống ke :))
ALTER PROC sp_getStatisticOverviewInMonth 
AS BEGIN
	DECLARE @totalOrder FLOAT = 0
	DECLARE @totalRent FLOAT = 0
	DECLARE @totalLost FLOAT = 0
	DECLARE @totalUser FLOAT = 0
	DECLARE @totalAddStorage FLOAT = 0
	DECLARE @totalIncome FLOAT = 0

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
	DECLARE @totalSumRentExpiration FLOAT = 0
	DECLARE @totalSumRent FLOAT = 0;
	DECLARE @totalSumMoneyStorage FLOAT = 0

	-- Tổng doanh thu bán sách
	SELECT @totalSumOrder = SUM(ORDER_DETAIL.amount*ORDER_DETAIL.price)
	FROM ORDER_DETAIL, [ORDER]
	WHERE ORDER_DETAIL.order_id = [ORDER].id
	AND YEAR([ORDER].date_created) = YEAR(GETDATE()) 
	AND MONTH([ORDER].date_created) = MONTH(GETDATE())

	-- Tổng phí phạt mất sách
	SELECT @totalSumLost = SUM(BOOK_LOST_DETAIL.cost)
	FROM BOOK_LOST, BOOK_LOST_DETAIL
	WHERE BOOK_LOST_DETAIL.rentbook_id = BOOK_LOST.rentbook_id
	AND YEAR(BOOK_LOST.created_date) = YEAR(GETDATE()) 
	AND MONTH(BOOK_LOST.created_date) = MONTH(GETDATE())
	
	--Lấy ra số tổng phí thuê sách
	SELECT @totalSumRent += cost_rent * SUM(amount)
	FROM dbo.RENTBOOK JOIN dbo.RENTBOOK_DETAIL
	ON RENTBOOK_DETAIL.rentbook_id = RENTBOOK.id
	WHERE YEAR(created_date) = YEAR(GETDATE()) AND MONTH(created_date) = MONTH(GETDATE())
	GROUP BY rentbook_id, cost_rent

	--Lấy ra số tổng phí phạt khi thuê
	SELECT @totalSumRentExpiration += cost_expiration * SUM(amount) * (DATEDIFF(DAY, created_date, returned_date) - expiration_day)
	FROM dbo.RENTBOOK JOIN dbo.RENTBOOK_DETAIL
	ON RENTBOOK_DETAIL.rentbook_id = RENTBOOK.id
	WHERE status = 1 AND DATEDIFF(DAY, created_date, returned_date) > expiration_day
	AND YEAR(created_date) = YEAR(GETDATE()) AND MONTH(created_date) = MONTH(GETDATE())
	GROUP BY rentbook_id, cost_expiration, created_date, returned_date, expiration_day

	--Lấy ra tổng chi phí khi nhập sách
	SELECT @totalSumMoneyStorage = SUM (price * amount)
	FROM dbo.STORAGE JOIN dbo.STORAGE_DETAIL 
	ON STORAGE_DETAIL.storage_id = STORAGE.id
	WHERE YEAR(created_date) = YEAR(GETDATE()) AND MONTH(created_date) = MONTH(GETDATE())

	SET @totalIncome = ISNULL(@totalSumOrder, 0) + ISNULL(@totalSumLost, 0) + ISNULL(@totalSumRentExpiration, 0) + ISNULL(@totalSumRent, 0) - ISNULL(@totalSumMoneyStorage, 0)

	SELECT @totalOrder AS [Total Order], @totalRent AS [Total Rent], @totalLost AS [Total Lost], @totalUser AS [Total User], @totalAddStorage AS [Total Book Storage], @totalIncome AS [Total imcome]
END
GO

EXEC sp_getStatisticOverviewInMonth
GO

/****** Object:  StoredProcedure  [sp_getStatisticOrderInMonth]  Script Date: 8/13/2019 ******/
--Trả về thông tin sách đã bán theo tháng // DROP PROC sp_getStatisticOrderInMonth EXEC sp_getStatisticOrderInMonth @month = 8
CREATE PROC sp_getStatisticOrderInMonth (@month int)
AS BEGIN
	DECLARE @bookId VARCHAR(50)
	DECLARE @bookTitle NVARCHAR(256),
	DECLARE @bookCategory NVARCHAR(256),
	DECLARE @bookAuthor NVARCHAR(256),
	DECLARE @bookCreatedDate DATE,
	DECLARE @totalOrder SMALLINT,
	DECLARE @totalBookOrder SMALLINT,
	DECLARE @totalPriceOrder MONEY

	DECLARE @tblStats TABLE
	(
		book_id VARCHAR(50),
		book_title NVARCHAR(256),
		book_category NVARCHAR(256),
		book_author NVARCHAR(256),
		book_createdDate DATE,
		totalOrder SMALLINT,
		totalBookOrder SMALLINT,
		totalPriceOrder MONEY
	)

	IF (@month != 0)
	BEGIN
		DECLARE cs CURSOR FOR SELECT book_id FROM dbo.[ORDER] JOIN dbo.ORDER_DETAIL
		ON ORDER_DETAIL.order_id = [ORDER].id
		WHERE YEAR(date_created) = YEAR(GETDATE()) AND MONTH(date_created) = @month
		GROUP BY book_id
	END
	ELSE
	BEGIN
		DECLARE cs CURSOR FOR SELECT book_id FROM dbo.[ORDER] JOIN dbo.ORDER_DETAIL
		ON ORDER_DETAIL.order_id = [ORDER].id
		WHERE YEAR(date_created) = YEAR(GETDATE())
		GROUP BY book_id
	END

	--duyệt list book vừa lấy ra được
	OPEN cs
	FETCH NEXT FROM cs INTO @bookId

	WHILE @@FETCH_STATUS = 0
	BEGIN
		

		FETCH NEXT FROM cs INTO @bookId
	END



	CLOSE cs
	DEALLOCATE cs
	
END
GO




/****** Object:  StoredProcedure  [sp_getStatisticRentbookInMonth]  Script Date: 8/13/2019 ******/
--Trả về thông tin sách thuê theo tháng
CREATE PROCEDURE sp_getStatisticRentbookInMonth (@month SMALLINT)
AS
BEGIN
	DECLARE @book_id VARCHAR(50)
	DECLARE @book_title NVARCHAR(256)
	DECLARE	@amount_total INT = 0
	DECLARE	@amount_returned INT = 0
	DECLARE	@amount_renting INT = 0
	DECLARE	@amount_expiration INT = 0

	DECLARE @tblStats TABLE
	(
		book_id VARCHAR(50),
		book_title NVARCHAR(256),
		amount_total INT,
		amount_returned INT,
		amount_renting INT,
		amount_expiration INT
	)
	--Tạo con trỏ duyệt từng sách có trong phần thuê sách
	IF (@month != 0) -- Nếu @month khác 0 thì duyệt lấy mã sách thuê theo tháng @month
		DECLARE cs  CURSOR FOR SELECT DISTINCT b.id 
		FROM dbo.BOOK b, dbo.RENTBOOK rb, dbo.RENTBOOK_DETAIL dt 
		WHERE b.id = dt.book_id AND rb.id = dt.rentbook_id
		AND YEAR(rb.created_date) = YEAR(GETDATE()) AND MONTH(rb.created_date) = @month
	ELSE-- Nếu @month = 0 thì duyệt lấy mã sách thuê trong cả năm
		DECLARE cs  CURSOR FOR SELECT DISTINCT b.id 
		FROM dbo.BOOK b, dbo.RENTBOOK rb, dbo.RENTBOOK_DETAIL dt 
		WHERE b.id = dt.book_id AND rb.id = dt.rentbook_id
		AND YEAR(rb.created_date) = YEAR(GETDATE())
	
	OPEN cs
	FETCH NEXT FROM cs INTO @book_id

	--Tiến hành lặp danh sách sách thuê và insert dữ liệu thống kê vào tblStats
	WHILE @@FETCH_STATUS = 0
	BEGIN
		-- Set giá trị các biến trở về mặc định
		SET	@amount_total = 0
		SET	@amount_returned = 0
		SET	@amount_renting = 0
		SET	@amount_expiration = 0

		IF (@month != 0)
		BEGIN
			--Tên sách
			SELECT @book_title = title FROM dbo.BOOK WHERE id = @book_id

			--Tổng số lượng đã thuê
			SELECT @amount_total = SUM(rdt.amount)
			FROM dbo.RENTBOOK_DETAIL rdt JOIN dbo.RENTBOOK
			ON RENTBOOK.id = rdt.rentbook_id
			WHERE rdt.book_id = @book_id
			AND YEAR(created_date) = YEAR(GETDATE()) AND MONTH(created_date) = @month
			GROUP BY rdt.book_id

			--Tổng số sách đã trả
			SELECT @amount_returned = SUM(amount)
			FROM dbo.RENTBOOK_DETAIL JOIN dbo.RENTBOOK
			ON RENTBOOK.id = RENTBOOK_DETAIL.rentbook_id
			WHERE book_id = @book_id 
			AND YEAR(created_date) = YEAR(GETDATE()) AND MONTH(created_date) = @month
			WHERE status = 1
			GROUP BY book_id

			--Tổng số sách đang còn thuê
			SELECT @amount_renting = SUM(rdt.amount)
			FROM dbo.RENTBOOK JOIN dbo.RENTBOOK_DETAIL
			ON RENTBOOK_DETAIL.rentbook_id = RENTBOOK.id
			WHERE book_id = @book_id
			AND status = 0
			AND YEAR(created_date) = YEAR(GETDATE()) AND MONTH(created_date) = @month
			GROUP BY book_id

			--Tổng số sách đang quá hạn
			SELECT @amount_expiration = SUM(rdt.amount)
			FROM dbo.RENTBOOK JOIN dbo.RENTBOOK_DETAIL
			WHERE book_id = @book_id
			AND rb.status = 0 
			AND DATEDIFF(DAY, rb.created_date, GETDATE()) > rb.expiration_day
			AND YEAR(created_date) = YEAR(GETDATE()) AND MONTH(created_date) = @month
			GROUP BY b.id
		END
		ELSE
		BEGIN
			--Tên sách
			SELECT @book_title = title FROM dbo.BOOK WHERE id = @book_id

			--Tổng số lượng đã thuê
			SELECT @amount_total = SUM(rdt.amount)
			FROM dbo.RENTBOOK_DETAIL rdt JOIN dbo.RENTBOOK
			ON RENTBOOK.id = rdt.rentbook_id
			WHERE rdt.book_id = @book_id
			AND YEAR(created_date) = YEAR(GETDATE())
			GROUP BY rdt.book_id

			--Tổng số sách đã trả
			SELECT @amount_returned = SUM(amount)
			FROM dbo.RENTBOOK_DETAIL JOIN dbo.RENTBOOK
			ON RENTBOOK.id = RENTBOOK_DETAIL.rentbook_id
			WHERE book_id = @book_id 
			AND YEAR(created_date) = YEAR(GETDATE())
			WHERE status = 1
			GROUP BY book_id

			--Tổng số sách đang còn thuê
			SELECT @amount_renting = SUM(rdt.amount)
			FROM dbo.RENTBOOK JOIN dbo.RENTBOOK_DETAIL
			ON RENTBOOK_DETAIL.rentbook_id = RENTBOOK.id
			WHERE book_id = @book_id
			AND status = 0
			AND YEAR(created_date) = YEAR(GETDATE())
			GROUP BY book_id

			--Tổng số sách đang quá hạn
			SELECT @amount_expiration = SUM(rdt.amount)
			FROM dbo.RENTBOOK JOIN dbo.RENTBOOK_DETAIL
			WHERE book_id = @book_id
			AND rb.status = 0 
			AND DATEDIFF(DAY, rb.created_date, GETDATE()) > rb.expiration_day
			AND YEAR(created_date) = YEAR(GETDATE())
			GROUP BY b.id
		END

		--Thêm các dữ liệu đã thống kê vào bảng tblStats
		INSERT @tblStats( book_id, book_title, amount_total, amount_returned, amount_renting, amount_expiration)
		VALUES  (@book_id, @book_title, @amount_total, @amount_returned, @amount_renting, @amount_expiration)

		-- Đi đến dòng tiếp theo
		FETCH NEXT FROM cs INTO @book_id
	END


	--Đóng con trỏ
	CLOSE cs
	DEALLOCATE cs

	--Trả về bảng danh sách đã thống kê
	SELECT * FROM @tblStats
END
GO

/****** Object:  StoredProcedure  [sp_getStatisticBookLostInMonth]  Script Date: 8/13/2019 ******/
--Trả về thông tin sách mất theo tháng
ALTER PROCEDURE sp_getStatisticBookLostInMonth(@month SMALLINT)
AS
BEGIN
	DECLARE @bookId VARCHAR(50)
	DECLARE @bookTitle NVARCHAR(256)
	DECLARE @totalBookLost SMALLINT = 0
	DECLARE @totalAmountBookLost SMALLINT = 0
	DECLARE @totalCost MONEY = 0

	DECLARE @tblStats TABLE
	(
		bookId VARCHAR(50),
		bookTitle NVARCHAR(256),
		totalBookLost SMALLINT,
		totalAmountBookLost SMALLINT,
		totalCost MONEY
	)

	--Tạo con trỏ duyệt qua các sách có trong khi báo mất
	IF (@month != 0)
	BEGIN
		DECLARE cs CURSOR FOR SELECT book_id
		FROM dbo.BOOK_LOST JOIN dbo.BOOK_LOST_DETAIL
		ON BOOK_LOST_DETAIL.rentbook_id = BOOK_LOST.rentbook_id
		WHERE YEAR(created_date) = YEAR(GETDATE()) AND MONTH(created_date) = @month
		GROUP BY book_id
	END
	ELSE
	BEGIN
		DECLARE cs CURSOR FOR SELECT book_id
		FROM dbo.BOOK_LOST JOIN dbo.BOOK_LOST_DETAIL
		ON BOOK_LOST_DETAIL.rentbook_id = BOOK_LOST.rentbook_id
		WHERE YEAR(created_date) = YEAR(GETDATE())
		GROUP BY book_id
	END

	--Tiến hành duyệt danh sách và lấy các con số thống kê
	OPEN cs
	FETCH NEXT FROM cs INTO @bookId

	WHILE @@FETCH_STATUS = 0
	BEGIN
		--Reset dữ liệu lại
		SET @totalBookLost = 0
		SET @totalAmountBookLost = 0
		SET @totalCost = 0

		IF (@month != 0)
		BEGIN
			--Tên sách
			SELECT @bookTitle = title FROM dbo.BOOK WHERE id = @bookId

			--Số đơn báo mất
			SELECT @totalBookLost = COUNT(*) FROM dbo.BOOK_LOST JOIN dbo.BOOK_LOST_DETAIL
			ON BOOK_LOST_DETAIL.rentbook_id = BOOK_LOST.rentbook_id
			WHERE book_id = @bookId
			AND YEAR(created_date) = YEAR(GETDATE()) AND MONTH(created_date) = @month

			--Tổng sách mất
			SELECT @totalAmountBookLost = SUM(amount) FROM dbo.BOOK_LOST JOIN dbo.BOOK_LOST_DETAIL
			ON BOOK_LOST_DETAIL.rentbook_id = BOOK_LOST.rentbook_id
			WHERE book_id = @bookId
			AND YEAR(created_date) = YEAR(GETDATE()) AND MONTH(created_date) = @month

			--Tổng tiền phạt thu được
			SELECT @totalCost = SUM(cost) FROM dbo.BOOK_LOST JOIN dbo.BOOK_LOST_DETAIL
			ON BOOK_LOST_DETAIL.rentbook_id = BOOK_LOST.rentbook_id
			WHERE book_id = @bookId
			AND YEAR(created_date) = YEAR(GETDATE()) AND MONTH(created_date) = @month
		END
		ELSE
		BEGIN
			--Tên sách
			SELECT @bookTitle = title FROM dbo.BOOK WHERE id = @bookId

			--Số đơn báo mất
			SELECT @totalBookLost = COUNT(*) FROM dbo.BOOK_LOST JOIN dbo.BOOK_LOST_DETAIL
			ON BOOK_LOST_DETAIL.rentbook_id = BOOK_LOST.rentbook_id
			WHERE book_id = @bookId
			AND YEAR(created_date) = YEAR(GETDATE())

			--Tổng sách mất
			SELECT @totalAmountBookLost = SUM(amount) FROM dbo.BOOK_LOST JOIN dbo.BOOK_LOST_DETAIL
			ON BOOK_LOST_DETAIL.rentbook_id = BOOK_LOST.rentbook_id
			WHERE book_id = @bookId
			AND YEAR(created_date) = YEAR(GETDATE())

			--Tổng tiền phạt thu được
			SELECT @totalCost = SUM(cost) FROM dbo.BOOK_LOST JOIN dbo.BOOK_LOST_DETAIL
			ON BOOK_LOST_DETAIL.rentbook_id = BOOK_LOST.rentbook_id
			WHERE book_id = @bookId
			AND YEAR(created_date) = YEAR(GETDATE())
		END
		
		--Thêm dữ liệu vừa thống kê được vào bảng tblStats
		INSERT @tblStats( bookId, bookTitle, totalBookLost, totalAmountBookLost, totalCost)
		VALUES  (@bookId, @bookTitle, @totalBookLost, @totalAmountBookLost, @totalCost)

		--Đi tới dòng tiếp theo
		FETCH NEXT FROM cs INTO @bookId
	END


	CLOSE cs
	DEALLOCATE cs

	SELECT * FROM @tblStats
END
GO


/****** Object:  StoredProcedure  [sp_getStatisticUserInMonth]  Script Date: 8/13/2019 ******/
--Trả về thông tin thành viên người dùng theo tháng
CREATE PROCEDURE sp_getStatisticUserInMonth(@month SMALLINT)
AS
BEGIN
	IF (@month != 0)
	BEGIN
		SELECT id, username, fullname, email, date_of_birth, created_date 
		FROM dbo.[USER] 
		WHERE YEAR(created_date) = YEAR(GETDATE()) AND MONTH(created_date) = @month
	END
	ELSE
	BEGIN
		SELECT id, username, fullname, email, date_of_birth, created_date 
		FROM dbo.[USER] 
		WHERE YEAR(created_date) = YEAR(GETDATE())
	END
END
GO

/****** Object:  StoredProcedure  [sp_getStatisticStorageInMonth]  Script Date: 8/13/2019 ******/
--Trả về thông tin sách nhập của tháng EXEC sp_getStatisticStorageInMonth 8
CREATE PROCEDURE sp_getStatisticStorageInMonth(@month SMALLINT)
AS
BEGIN
	DECLARE @bookId VARCHAR(50)
	DECLARE @bookTitle NVARCHAR(256)
	DECLARE	@totalAmount SMALLINT = 0
	DECLARE @totalPrice MONEY = 0

	DECLARE @tblStats TABLE
	(
		bookId VARCHAR(50),
		bookTitle NVARCHAR(256),
		totalAmount SMALLINT,
		totalPrice MONEY
	)

	--Tạo con trỏ, lấy về danh sách book_id cần thống kê
	IF (@month != 0)
	BEGIN
		DECLARE cs CURSOR FOR SELECT book_id
		FROM dbo.STORAGE JOIN dbo.STORAGE_DETAIL
		ON STORAGE_DETAIL.storage_id = STORAGE.id
		WHERE YEAR(created_date) = YEAR(GETDATE()) AND MONTH(created_date) = @month
		GROUP BY book_id
	END
	ELSE 
	BEGIN
		DECLARE cs CURSOR FOR SELECT book_id
		FROM dbo.STORAGE JOIN dbo.STORAGE_DETAIL
		ON STORAGE_DETAIL.storage_id = STORAGE.id
		WHERE YEAR(created_date) = YEAR(GETDATE())
		GROUP BY book_id
	END
	
	--Duyệt danh sách book_id
	OPEN cs
	FETCH NEXT FROM cs INTO @bookId

	WHILE @@FETCH_STATUS = 0
	BEGIN
		-- Reset dữ liệu các biến tạm
		SET	@totalAmount = 0
		SET @totalPrice = 0

		IF (@month != 0)
		BEGIN
			--Tên sách
			SELECT @bookTitle = title FROM dbo.BOOK
			WHERE id = @bookId

			--Tổng sách đã nhập
			SELECT @totalAmount = SUM (amount) FROM dbo.STORAGE JOIN dbo.STORAGE_DETAIL
			ON STORAGE_DETAIL.storage_id = STORAGE.id
			WHERE book_id = @bookId
			AND YEAR(created_date) = YEAR(GETDATE()) AND MONTH(created_date) = @month

			--Tổng chi phí
			SELECT @totalPrice = SUM (amount * price) FROM dbo.STORAGE JOIN dbo.STORAGE_DETAIL
			ON STORAGE_DETAIL.storage_id = STORAGE.id
			WHERE book_id = @bookId
			AND YEAR(created_date) = YEAR(GETDATE()) AND MONTH(created_date) = @month
		END
		ELSE
		BEGIN
			--Tên sách
			SELECT @bookTitle = title FROM dbo.BOOK
			WHERE id = @bookId

			--Tổng sách đã nhập
			SELECT @totalAmount = SUM (amount) FROM dbo.STORAGE JOIN dbo.STORAGE_DETAIL
			ON STORAGE_DETAIL.storage_id = STORAGE.id
			WHERE book_id = @bookId
			AND YEAR(created_date) = YEAR(GETDATE())

			--Tổng chi phí
			SELECT @totalPrice = SUM (amount * price) FROM dbo.STORAGE JOIN dbo.STORAGE_DETAIL
			ON STORAGE_DETAIL.storage_id = STORAGE.id
			WHERE book_id = @bookId
			AND YEAR(created_date) = YEAR(GETDATE())
		END

		--Insert dữ liệu tìm được vào bảng stats
		INSERT @tblStats( bookId, bookTitle, totalAmount, totalPrice)
		VALUES  (@bookId, @bookTitle, @totalAmount, @totalPrice)

		-- Nhảy tới dòng kế tiếp
		FETCH NEXT FROM cs INTO @bookId
	END

	CLOSE cs
	DEALLOCATE cs

	SELECT * FROM @tblStats
END
GO

/****** Object:  StoredProcedure  [sp_getStatisticIncome]  Script Date: 8/13/2019 ******/
--Trả về thông tin doanh thu
CREATE PROCEDURE sp_getStatisticIncome (@month SMALLINT)
AS
BEGIN
	DECLARE @book_id VARCHAR(50)
	DECLARE @book_title NVARCHAR(256)
	DECLARE	@total_cost_storage MONEY = 0
	DECLARE	@total_money_order MONEY = 0
	DECLARE	@total_money_rentbook MONEY = 0
	DECLARE	@total_money_penalty MONEY = 0
	DECLARE	@total_money_income MONEY = 0

	DECLARE @tblStats TABLE
	(
		book_id VARCHAR(50),
		book_title NVARCHAR(256),
		total_cost_storage MONEY,
		total_money_order MONEY,
		total_money_rentbook MONEY,
		total_money_penalty MONEY,
		total_money_income MONEY
	)

	-- Lấy ra danh sách bảng chứa book_id tồn tại trong 1 trong số các bảng cần thống kê
	IF (@month != 0)
	BEGIN
		DECLARE cs CURSOR FOR SELECT id FROM dbo.BOOK
		WHERE id IN
		(
			SELECT DISTINCT book_id
			FROM dbo.RENTBOOK JOIN dbo.RENTBOOK_DETAIL 
			ON RENTBOOK_DETAIL.rentbook_id = RENTBOOK.id
			WHERE YEAR(created_date) = YEAR(GETDATE()) AND MONTH(created_date) = @month
		) OR id IN
		(
			SELECT DISTINCT book_id
			FROM dbo.[ORDER] JOIN dbo.ORDER_DETAIL 
			ON ORDER_DETAIL.order_id = [ORDER].id
			WHERE YEAR(created_date) = YEAR(GETDATE()) AND MONTH(created_date) = @month
		) OR id IN
		(
			SELECT DISTINCT book_id
			FROM dbo.STORAGE JOIN dbo.STORAGE_DETAIL
			ON STORAGE_DETAIL.storage_id = STORAGE.id
			WHERE YEAR(created_date) = YEAR(GETDATE()) AND MONTH(created_date) = @month
		) OR id IN
		(
			SELECT DISTINCT book_id
			FROM dbo.BOOK_LOST JOIN dbo.BOOK_LOST_DETAIL
			ON BOOK_LOST_DETAIL.rentbook_id = BOOK_LOST.rentbook_id
			WHERE YEAR(created_date) = YEAR(GETDATE()) AND MONTH(created_date) = @month
		)
	END
	ELSE
	BEGIN
		DECLARE cs CURSOR FOR SELECT id FROM dbo.BOOK
		WHERE id IN
		(
			SELECT DISTINCT book_id
			FROM dbo.RENTBOOK JOIN dbo.RENTBOOK_DETAIL 
			ON RENTBOOK_DETAIL.rentbook_id = RENTBOOK.id
			WHERE YEAR(created_date) = YEAR(GETDATE())
		) OR id IN
		(
			SELECT DISTINCT book_id
			FROM dbo.[ORDER] JOIN dbo.ORDER_DETAIL 
			ON ORDER_DETAIL.order_id = [ORDER].id
			WHERE YEAR(created_date) = YEAR(GETDATE())
		) OR id IN
		(
			SELECT DISTINCT book_id
			FROM dbo.STORAGE JOIN dbo.STORAGE_DETAIL
			ON STORAGE_DETAIL.storage_id = STORAGE.id
			WHERE YEAR(created_date) = YEAR(GETDATE())
		) OR id IN
		(
			SELECT DISTINCT book_id
			FROM dbo.BOOK_LOST JOIN dbo.BOOK_LOST_DETAIL
			ON BOOK_LOST_DETAIL.rentbook_id = BOOK_LOST.rentbook_id
			WHERE YEAR(created_date) = YEAR(GETDATE())
		)
	END
	
	--Duyệt con trỏ và tiến hành lấy các giá trị cần thống kê
	OPEN cs
	FETCH NEXT FROM cs INTO @book_id

	-- Lặp qua hết danh sách book_id vừa lấy được
	WHILE @@FETCH_STATUS = 0
	BEGIN
		SET	@total_cost_storage = 0
		SET	@total_money_order = 0
		SET	@total_money_rentbook = 0
		SET	@total_money_penalty = 0
		SET	@total_money_income = 0
					DECLARE @totalPriceRent MONEY = 0
			DECLARE @costRentExpiration MONEY = 0
			DECLARE @costBookLost MONEY = 0;

		IF (@month != 0)
		BEGIN
			-- /////////// TÊN SÁCH ///////////////
			SELECT @book_title = title FROM dbo.BOOK

			-- /////////// TIỀN NHẬP /////////////
			SELECT @total_cost_storage = SUM (SDT.amount * SDT.price)
			FROM dbo.STORAGE S JOIN dbo.STORAGE_DETAIL SDT 
			ON SDT.storage_id = S.id
			WHERE SDT.book_id = @book_id 
			AND YEAR(S.created_date) = YEAR(GETDATE()) AND MONTH(S.created_date) = @month
		
			-- /////////// TIỀN BÁN /////////////
			SELECT @total_money_order = SUM (amount * price)
			FROM dbo.[ORDER] JOIN dbo.ORDER_DETAIL 
			ON ORDER_DETAIL.order_id = [ORDER].id
			WHERE book_id = @book_id 
			AND YEAR(date_created) = YEAR(GETDATE()) AND MONTH(date_created) = @month

			--/////////// TIỀN THUÊ /////////////

			SELECT @totalPriceRent += cost_rent * SUM(amount)
			FROM dbo.RENTBOOK JOIN dbo.RENTBOOK_DETAIL
			ON RENTBOOK_DETAIL.rentbook_id = RENTBOOK.id
			WHERE book_id = @book_id
			AND YEAR(created_date) = YEAR(GETDATE()) AND MONTH(created_date) = @month
			GROUP BY id, cost_rent 
		
			SET @total_money_rentbook = @totalPriceRent

			-- /////////////  TỔNG TIỀN PHẠT //////////////////

			--Lấy ra tổng phí thu khi phạt mất sách
			SELECT @costBookLost += bl.cost_lost * bldt.amount * rbdt.price
			FROM dbo.BOOK_LOST bl, dbo.BOOK_LOST_DETAIL bldt, dbo.RENTBOOK_DETAIL rbdt
			WHERE bl.rentbook_id = bldt.rentbook_id 
			AND bldt.rentbook_id = rbdt.rentbook_id 
			AND bldt.book_id = rbdt.book_id
			AND bldt.book_id = @book_id
			AND YEAR(bl.created_date) = YEAR(GETDATE()) AND MONTH(bl.created_date) = @month
			GROUP BY bl.rentbook_id,  bldt.amount, rbdt.price, bl.cost_lost

			--Lấy ra tổng phí thu khi phạt quá hạn - ĐÃ TRẢ
			SELECT @costRentExpiration += rb.cost_expiration * SUM(dt.amount) * (DATEDIFF(DAY, rb.created_date, rb.returned_date) - rb.expiration_day)
			FROM dbo.RENTBOOK rb, dbo.RENTBOOK_DETAIL dt
			WHERE rb.id = dt.rentbook_id 
			AND rb.status = 1 
			AND DATEDIFF(DAY, rb.created_date, rb.returned_date) > rb.expiration_day
			AND dt.book_id = @book_id
			AND YEAR(rb.created_date) = YEAR(GETDATE()) AND MONTH(rb.created_date) = @month
			GROUP BY rb.id, rb.cost_expiration, rb.created_date, rb.returned_date, rb.expiration_day

			-- Tổng phí nhận được từ phí phạt
			SET @total_money_penalty = ISNULL(@costBookLost, 0) + ISNULL(@costRentExpiration, 0)

			-- /////////////  TỔNG TIỀN  //////////////////
			SET @total_money_income = ISNULL(@total_money_order, 0) + ISNULL(@total_money_penalty, 0) + ISNULL(@total_money_rentbook, 0) - ISNULL(@total_cost_storage, 0)
		END 
		ELSE
		BEGIN
			-- /////////// TÊN SÁCH ///////////////
			SELECT @book_title = title FROM dbo.BOOK

			-- /////////// TIỀN NHẬP /////////////
			SELECT @total_cost_storage = SUM (SDT.amount * SDT.price)
			FROM dbo.STORAGE S JOIN dbo.STORAGE_DETAIL SDT 
			ON SDT.storage_id = S.id
			WHERE SDT.book_id = @book_id 
			AND YEAR(S.created_date) = YEAR(GETDATE())
		
			-- /////////// TIỀN BÁN /////////////
			SELECT @total_money_order = SUM (amount * price)
			FROM dbo.[ORDER] JOIN dbo.ORDER_DETAIL 
			ON ORDER_DETAIL.order_id = [ORDER].id
			WHERE book_id = @book_id 
			AND YEAR(date_created) = YEAR(GETDATE())

			--/////////// TIỀN THUÊ /////////////

			SELECT @totalPriceRent += cost_rent * SUM(amount)
			FROM dbo.RENTBOOK JOIN dbo.RENTBOOK_DETAIL
			ON RENTBOOK_DETAIL.rentbook_id = RENTBOOK.id
			WHERE book_id = @book_id
			AND YEAR(created_date) = YEAR(GETDATE())
			GROUP BY id, cost_rent 
		
			SET @total_money_rentbook = @totalPriceRent

			-- /////////////  TỔNG TIỀN PHẠT //////////////////

			--Lấy ra tổng phí thu khi phạt mất sách
			SELECT @costBookLost += bl.cost_lost * bldt.amount * rbdt.price
			FROM dbo.BOOK_LOST bl, dbo.BOOK_LOST_DETAIL bldt, dbo.RENTBOOK_DETAIL rbdt
			WHERE bl.rentbook_id = bldt.rentbook_id 
			AND bldt.rentbook_id = rbdt.rentbook_id 
			AND bldt.book_id = rbdt.book_id
			AND bldt.book_id = @book_id
			AND YEAR(bl.created_date) = YEAR(GETDATE())
			GROUP BY bl.rentbook_id,  bldt.amount, rbdt.price, bl.cost_lost

			--Lấy ra tổng phí thu khi phạt quá hạn - ĐÃ TRẢ
			SELECT @costRentExpiration += rb.cost_expiration * SUM(dt.amount) * (DATEDIFF(DAY, rb.created_date, rb.returned_date) - rb.expiration_day)
			FROM dbo.RENTBOOK rb, dbo.RENTBOOK_DETAIL dt
			WHERE rb.id = dt.rentbook_id 
			AND rb.status = 1 
			AND DATEDIFF(DAY, rb.created_date, rb.returned_date) > rb.expiration_day
			AND dt.book_id = @book_id
			AND YEAR(rb.created_date) = YEAR(GETDATE())
			GROUP BY rb.id, rb.cost_expiration, rb.created_date, rb.returned_date, rb.expiration_day

			-- Tổng phí nhận được từ phí phạt
			SET @total_money_penalty = ISNULL(@costBookLost, 0) + ISNULL(@costRentExpiration, 0)

			-- /////////////  TỔNG TIỀN  //////////////////
			SET @total_money_income = ISNULL(@total_money_order, 0) + ISNULL(@total_money_penalty, 0) + ISNULL(@total_money_rentbook, 0) - ISNULL(@total_cost_storage, 0)
		END
		

		--Thêm dữ liệu thống kê từ book_id đó vào bảng thống kê stats
		INSERT @tblStats( book_id, book_title, total_cost_storage, total_money_order, total_money_rentbook, total_money_penalty ,total_money_income)
		VALUES  (@book_id, @book_title, @total_cost_storage, @total_money_order, @total_money_rentbook, @total_money_penalty, @total_money_income)

		--Nhảy tới dòng kế tiếp
		FETCH NEXT FROM cs INTO @book_id
	END

	CLOSE cs
	DEALLOCATE cs

	--Trả về bảng danh sách đã thống kê
	DECLARE @SUM_TOTAL_INCOMEA MONEY

	SELECT @SUM_TOTAL_INCOMEA = SUM(total_money_income) FROM @tblStats
	SELECT *, @SUM_TOTAL_INCOMEA FROM @tblStats
END
GO

INSERT dbo.ADMIN
        ( username ,
          password ,
          fullName ,
          email ,
          phone_number ,
          image ,
          sex ,
          role ,
          isActive ,
          created_date
        )
VALUES  ( 'haogd' , -- username - varchar(50)
          '123456789' , -- password - varchar(50)
          N'Đại Hào' , -- fullName - nvarchar(256)
          'daihao12mc@gmail.com' , -- email - varchar(50)
          '0376555796' , -- phone_number - varchar(14)
          N'' , -- image - nvarchar(256)
          1 , -- sex - bit
          0 , -- role - int
          1 , -- isActive - bit
          GETDATE()  -- created_date - date
        )

GO

--Gọi khi bảng order detail có insert	
CREATE TRIGGER tg_InsertOrderDetail ON dbo.ORDER_DETAIL
FOR INSERT
AS
BEGIN
	DECLARE @book_id VARCHAR(50)
	DECLARE @amount_insertd INT
	DECLARE cs CURSOR FOR SELECT Inserted.book_id, Inserted.amount FROM Inserted
	
	OPEN cs
	FETCH NEXT FROM cs INTO @book_id, @amount_insertd

	WHILE @@FETCH_STATUS = 0
	BEGIN
		--Giảm trừ số lượng đã insert vào số lượng sách đang có
		UPDATE dbo.BOOK SET amount = (amount - @amount_insertd) WHERE id = @book_id

		--Di chuyển tới dòng tiếp theo
		FETCH NEXT FROM cs INTO @book_id, @amount_insertd
	END
	CLOSE cs
	DEALLOCATE cs
END
GO

--Gọi khi bảng order detail có delete	
CREATE TRIGGER tg_DeleteOrderDetail ON dbo.ORDER_DETAIL
FOR DELETE
AS
BEGIN
	DECLARE @book_id VARCHAR(50)
	DECLARE @amount_deleted INT
	DECLARE cs CURSOR FOR SELECT Deleted.book_id, Deleted.amount FROM Deleted
	OPEN cs

	FETCH NEXT FROM cs INTO @book_id, @amount_deleted
	WHILE @@FETCH_STATUS = 0
	BEGIN
		--Tăng số lượng từ bảng đã xóa vào số lượng sách đang có
		UPDATE dbo.BOOK SET amount = (amount + @amount_deleted) WHERE id = @book_id
		FETCH NEXT FROM cs INTO @book_id, @amount_deleted
	END

	CLOSE cs
	DEALLOCATE cs	
END
GO

--Gọi khi bảng RentBookDetail có insert	
CREATE TRIGGER tg_insertRentBookDetail ON dbo.RENTBOOK_DETAIL
FOR INSERT
AS
BEGIN
	DECLARE @book_id VARCHAR(50)
	DECLARE @amount_inserted INT
	DECLARE cs CURSOR FOR SELECT Inserted.book_id, Inserted.amount FROM Inserted
	OPEN cs

	FETCH NEXT FROM cs INTO @book_id, @amount_inserted

	WHILE @@FETCH_STATUS = 0
	BEGIN
		--Giảm số lượng sách đang có dựa vào số lượng sách thuê đã insert
		UPDATE dbo.BOOK SET amount = (amount  - @amount_inserted) WHERE id = @book_id

		--Đi tới dòng tiếp theo
		FETCH NEXT FROM cs INTO @book_id, @amount_inserted
	END

	CLOSE cs
	DEALLOCATE cs
END
GO

--Gọi khi bảng RentBookDetail có delete	
CREATE TRIGGER tg_deleteRentBookDetail ON dbo.RENTBOOK_DETAIL
FOR DELETE
AS
BEGIN
	DECLARE @book_id VARCHAR(50)
	DECLARE @amount_deleted INT
	DECLARE @rentbook_id INT
	DECLARE @status INT
	DECLARE cs CURSOR FOR SELECT Deleted.book_id, Deleted.amount FROM Deleted
	
	-- Nếu xóa chi tiết của đơn thuê đã trả rồi thì ko xử lý nữa
	SELECT @status = status FROM dbo.RENTBOOK WHERE id = @rentbook_id
	IF (@status = 1)
		ROLLBACK

	OPEN cs
	FETCH NEXT FROM cs INTO @book_id, @amount_deleted

	WHILE @@FETCH_STATUS = 0
	BEGIN
		--tăng số lượng sách đang có dựa vào số lượng sách thuê đã delete
		UPDATE dbo.BOOK SET amount = (amount + @amount_deleted) WHERE id = @book_id

		--Di chuyển tới dòng kế tiếp
		FETCH NEXT FROM cs INTO @book_id, @amount_deleted
	END

	CLOSE cs
	DEALLOCATE cs
END
GO

--Gọi khi bảng RENTBOOK có update status = 1 => đã trả sách
CREATE TRIGGER tg_updateRentBook ON dbo.RENTBOOK
FOR UPDATE
AS
BEGIN
	DECLARE @status_before INT
	DECLARE @status_after INT
	DECLARE @rentbook_id INT
	DECLARE @book_id VARCHAR(50)
	DECLARE @amount INT

	--Lấy ra trạng thái sau khi cập nhật và trước đó
	SELECT @status_before = Deleted.status, @rentbook_id = Deleted.id FROM Deleted
	SELECT @status_after = Inserted.status FROM Inserted



	--Mã status = 1 => đã trả sách
	IF (@status_before = 0 AND @status_after = 1)
	BEGIN
	
		DECLARE cs CURSOR FOR SELECT dt.book_id, dt.amount 
		FROM dbo.RENTBOOK_DETAIL dt WHERE rentbook_id = @rentbook_id

		OPEN cs
		FETCH NEXT FROM cs INTO @book_id, @amount

		WHILE @@FETCH_STATUS = 0
		BEGIN
			--Lấy số lượng trong chi tiết + ngược lại vào số lượng sách đang có
			UPDATE dbo.BOOK SET amount = (amount + @amount) WHERE id = @book_id

			--Duyệt tiếp
			FETCH NEXT FROM cs INTO @book_id, @amount
		END

		CLOSE cs
		DEALLOCATE cs	
	END
END
GO

--Gọi khi bảng BOOK_LOST_DETAIL có insert	
CREATE TRIGGER tg_insertBookLostDetail ON dbo.BOOK_LOST_DETAIL
FOR INSERT
AS
BEGIN
	DECLARE @book_id VARCHAR(50)
	DECLARE @amount_inserted INT
	DECLARE cs CURSOR FOR SELECT Inserted.book_id, Inserted.amount FROM Inserted
	
	OPEN cs
	FETCH NEXT FROM cs INTO @book_id, @amount_inserted

	WHILE @@FETCH_STATUS = 0
	BEGIN
		--Giảm số lượng sách đang có dựa vào số lượng sách mất đã insert
		UPDATE dbo.BOOK SET amount = (amount  - @amount_inserted) WHERE id = @book_id
		
		--Next tới dòng kế tiếp
		FETCH NEXT FROM cs INTO @book_id, @amount_inserted  
	END

	CLOSE cs
	DEALLOCATE cs
END
GO

--Gọi khi bảng BOOK_LOST_DETAIL có delete	
CREATE TRIGGER tg_deleteBookLostDetail ON dbo.BOOK_LOST_DETAIL
FOR DELETE
AS
BEGIN
	DECLARE @book_id VARCHAR(50)
	DECLARE @amount_deleted INT
	DECLARE cs CURSOR FOR SELECT Deleted.book_id, Deleted.amount FROM Deleted

	OPEN cs
	FETCH NEXT FROM cs INTO @book_id, @amount_deleted

	WHILE @@FETCH_STATUS = 0
	BEGIN
		--tăng số lượng sách đang có dựa vào số lượng sách mất đã deleted
		UPDATE dbo.BOOK SET amount = (amount  + @amount_deleted) WHERE id = @book_id

		--Next tới dòng kế tiếp
		FETCH NEXT FROM cs INTO @book_id, @amount_deleted
	END

	CLOSE cs
	DEALLOCATE cs
END
GO

--Gọi khi bảng STORAGE_DETAIL có INSERT
CREATE TRIGGER tg_InsertStorageDetail ON dbo.STORAGE_DETAIL 
FOR INSERT 
AS
BEGIN
	DECLARE @book_id VARCHAR(50)
	DECLARE @amount_insertd INT

	--Lấy ra mã sách và số lượng insert
	Declare cs Cursor
		FOR SELECT Inserted.book_id, Inserted.amount FROM Inserted
	Open cs
	Fetch next from cs INTO @book_id, @amount_insertd
	While @@FETCH_STATUS = 0
	BEGIN
		--Thêm số lượng đã insert vào số lượng sách đang có
		UPDATE dbo.BOOK SET amount = (amount + @amount_insertd) WHERE id = @book_id
		
		-- Next dòng tiếp theo
		Fetch next from cs INTO @book_id, @amount_insertd
	END
	close cs -- đóng con trỏ
	deallocate cs -- hủy bộ nhớ
END
GO

--Gọi khi bảng STORAGE_DETAIL có delete
CREATE TRIGGER tg_DeleteStorageDetail ON dbo.STORAGE_DETAIL
FOR DELETE
AS
BEGIN
	DECLARE @book_id VARCHAR(50)
	DECLARE @amount_deleted INT
	DECLARE cs CURSOR FOR SELECT Deleted.book_id, Deleted.amount FROM Deleted

	OPEN cs
	FETCH NEXT FROM cs INTO @book_id, @amount_deleted

	WHILE @@FETCH_STATUS = 0
	BEGIN
		--Giảm số lượng từ bảng đã xóa vào số lượng sách đang có
		UPDATE dbo.BOOK SET amount = (amount - @amount_deleted) WHERE id = @book_id
		--Di chuyển tới dòng kế tiếp
		FETCH NEXT FROM cs INTO @book_id, @amount_deleted
	END
	CLOSE cs
	DEALLOCATE cs
END
GO

SELECT * FROM dbo.RENTBOOK_DETAIL WHERE rentbook_id = 102






/*


SELECT * FROM dbo.RENTBOOK
UPDATE dbo.RENTBOOK SET status = 1 WHERE id = 100


SELECT * FROM dbo.BOOK
SELECT * FROM dbo.[ORDER_DETAIL]
SELECT * FROM dbo.LOCATION
SELECT * FROM dbo.AUTHOR
SELECT * FROM dbo.PUBLISHER

UPDATE dbo.BOOK SET description = 'abcdef descrip' WHERE id = 'GH12'

SELECT * FROM dbo.[USER]
SELECT * FROM dbo.ADMIN
SELECT * FROM dbo.[ORDER]
SELECT * FROM dbo.ORDER_DETAIL
SELECT * FROM dbo.RENTBOOK
SELECT * FROM dbo.RENTBOOK_DETAIL
SELECT * FROM dbo.STORAGE_DETAIL
SELECT * FROM BOOK_LOST
DELETE FROM dbo.RENTBOOK
261aa
PRINT DATEDIFF(DAY, '3/1/2011', '3/1/2011')

UPDATE dbo.BOOK SET amount = 0
DELETE FROM dbo.STORAGE_DETAIL WHERE storage_id = 103 
GO

SELECT * FROM dbo.BOOK
SELECT * FROM dbo.ORDER_DETAIL

SELECT * FROM dbo.STORAGE
SELECT * FROM dbo.STORAGE_DETAIL


INSERT dbo.STORAGE( admin_id ,description ,created_date)
VALUES  ( 101 , N'test' , GETDATE() )

INSERT dbo.STORAGE_DETAIL( storage_id, book_id, amount, price )
VALUES  ( 103, 'GH12', 10,  100000 )

INSERT dbo.STORAGE_DETAIL( storage_id, book_id, amount, price )
VALUES	( 103, 'JH42', 10,  100000 ),
		( 103, 'GH12', 10,  100000 )
*/


