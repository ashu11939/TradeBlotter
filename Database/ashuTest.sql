#SELECT * FROM mydatabase.pricing_info_with_entitlement;

Select * 
from pricing_info_with_entitlement p
where p.TimeUpdated >= '2016-05-20' and p.TimeUpdated <= '2016-05-22' and p.entitlement >=1;