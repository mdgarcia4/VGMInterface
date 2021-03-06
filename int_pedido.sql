/*
   jueves, 23 de julio de 20202:01:25
   Usuario: 
   Servidor: (local)
   Base de datos: despo
   Aplicación: 
*/

/* Para evitar posibles problemas de pérdida de datos, debe revisar este script detalladamente antes de ejecutarlo fuera del contexto del diseñador de base de datos.*/
BEGIN TRANSACTION
SET QUOTED_IDENTIFIER ON
SET ARITHABORT ON
SET NUMERIC_ROUNDABORT OFF
SET CONCAT_NULL_YIELDS_NULL ON
SET ANSI_NULLS ON
SET ANSI_PADDING ON
SET ANSI_WARNINGS ON
COMMIT
BEGIN TRANSACTION
GO
CREATE TABLE dbo.Table_1
	(
	id_pedido int NOT NULL IDENTITY (1, 1),
	id_codigo_distribuidor varchar(255) NULL,
	id_cliente_tienda varchar(255) NULL,
	id_pedido_interface varchar(32) NULL,
	fe_pedido datetime NULL,
	ca_total_pedido decimal(12, 4) NULL,
	de_cliente_nombre varchar(255) NULL,
	de_cliente_apellido varchar(255) NULL,
	de_cliente_mail varchar(255) NULL,
	pr_total decimal(20, 4) NULL,
	pr_subtotal_sdto decimal(20, 4) NULL,
	pr_descuento decimal(20, 4) NULL,
	ca_items int NULL,
	pr_subtotal_cdto decimal(20, 4) NULL,
	de_direccion_ip varchar(255) NULL
	)  ON [PRIMARY]
GO
ALTER TABLE dbo.Table_1 ADD CONSTRAINT
	PK_Table_1 PRIMARY KEY CLUSTERED 
	(
	id_pedido
	) WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]

GO
ALTER TABLE dbo.Table_1 SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
