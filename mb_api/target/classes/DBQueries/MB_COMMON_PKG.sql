CREATE OR REPLACE PACKAGE MB_COMMON_PKG AS    
   PROCEDURE add_item(i_itm_model varchar2, i_itm_sku NUMBER, i_itm_desc varchar2, p_error_code number, p_error_msg varchar2); 


   
   
   
END MB_COMMON_PKG; 
/ 