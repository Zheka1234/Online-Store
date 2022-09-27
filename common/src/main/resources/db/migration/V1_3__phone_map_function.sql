drop function if exists get_map_of_phone(boolean);

create or replace function get_map_of_phone(in_stock_param boolean) returns double precision
    language sql
as
$$ select price
   from phoneshop.phone
   where in_stock = in_stock_param;
$$;

alter function get_map_of_phone(boolean) owner to postgres;