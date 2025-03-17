
select * 
from SalesInvoice

select *
from Cars


SELECT YEAR(invoiceDate) AS Year, COUNT(carID) AS CarsSold
FROM SalesInvoice
GROUP BY YEAR(invoiceDate)
ORDER BY Year DESC;


SELECT YEAR(invoiceDate) AS Year, SUM(price) AS Revenue
FROM SalesInvoice
JOIN Cars ON SalesInvoice.carID = Cars.carID
GROUP BY YEAR(invoiceDate)
ORDER BY Year DESC;

select *
from Cars

 -- L?y top 10 m?u xe bán ch?y nh?t
SELECT top 10 model, COUNT(SalesInvoice.carID) AS TotalSold
FROM SalesInvoice
JOIN Cars ON SalesInvoice.carID = Cars.carID
GROUP BY model
ORDER BY TotalSold DESC


-- L?y top 10 ph? tùng ðý?c s? d?ng nhi?u nh?t
SELECT top 10 partName, SUM(numberUsed) AS TotalUsed
FROM PartsUsed
JOIN Parts ON PartsUsed.partID = Parts.partID
GROUP BY partName
ORDER BY TotalUsed DESC


select mechanicID, COUNT(*) as total
from ServiceMehanic
group by mechanicID
order by total desc


SELECT top 3 mechanicName, COUNT(serviceTicketID) AS RepairsHandled
FROM ServiceMehanic
JOIN Mechanic ON ServiceMehanic.mechanicID = Mechanic.mechanicID
GROUP BY mechanicName
ORDER BY RepairsHandled DESC




