CREATE TABLE [dbo].[animateurs](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nom] [nvarchar](50) NULL,
	[prenom] [nvarchar](50) NULL,
	[email] [nvarchar](50) NULL,
	[motdepasse] [nvarchar](50) NULL,
 CONSTRAINT [pk_animateurs] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[animateurs] ON
INSERT [dbo].[animateurs] ([id], [nom], [prenom], [email], [motdepasse]) VALUES (1, N'Dupont', N'Jean', N'jd', N'jd')
INSERT [dbo].[animateurs] ([id], [nom], [prenom], [email], [motdepasse]) VALUES (2, N'Durand', N'Marc', N'mdurand@sonmail.fr', N'md')
SET IDENTITY_INSERT [dbo].[animateurs] OFF
/****** Object:  Table [dbo].[formations]    Script Date: 05/29/2016 22:20:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[formations](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[libelle] [nvarchar](50) NULL,
	[description] [nvarchar](500) NULL,
	[debut] [datetime] NULL,
	[fin] [datetime] NULL,
 CONSTRAINT [pk_formations] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[formations] ON
INSERT [dbo].[formations] ([id], [libelle], [description], [debut], [fin]) VALUES (1, N'Java SE', N'A l''issue de cette formation, vous maîtriserez le langage Java et serez capable de développer des applications complètes avec une interface graphique riche.', CAST(0x00009E6700000000 AS DateTime), CAST(0x00009E6600000000 AS DateTime))
INSERT [dbo].[formations] ([id], [libelle], [description], [debut], [fin]) VALUES (2, N'Java EE', N'A l''issue de cette formation, vous serez capable de développer des applications Web en utilisant des composants JavaEE', CAST(0x00009E8300000000 AS DateTime), CAST(0x00009E8E00000000 AS DateTime))
INSERT [dbo].[formations] ([id], [libelle], [description], [debut], [fin]) VALUES (3, N'.Net', N'A l''issue de cette formation, vous serez capable de développer des applications Web en utilisant des composants .Net', CAST(0x00009F3D00000000 AS DateTime), CAST(0x00009F4800000000 AS DateTime))
INSERT [dbo].[formations] ([id], [libelle], [description], [debut], [fin]) VALUES (4, N'java', N'module java', CAST(0x0000A5A900000000 AS DateTime), CAST(0x0000A5A900000000 AS DateTime))
SET IDENTITY_INSERT [dbo].[formations] OFF
/****** Object:  Table [dbo].[stagiaires]    Script Date: 05/29/2016 22:20:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[stagiaires](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nom] [nvarchar](50) NULL,
	[prenom] [nvarchar](50) NULL,
	[email] [nvarchar](50) NULL,
	[motdepasse] [nvarchar](50) NULL,
 CONSTRAINT [pk_stagiaires] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[stagiaires] ON
INSERT [dbo].[stagiaires] ([id], [nom], [prenom], [email], [motdepasse]) VALUES (1, N'Lerouge', N'Richard', N'rl', N'rl')
INSERT [dbo].[stagiaires] ([id], [nom], [prenom], [email], [motdepasse]) VALUES (2, N'Charpentier', N'Pierre', N'pcharpentier@sonmail.fr', N'pc')
SET IDENTITY_INSERT [dbo].[stagiaires] OFF
/****** Object:  Table [dbo].[inscriptions]    Script Date: 05/29/2016 22:20:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[inscriptions](
	[stagiaire] [int] NOT NULL,
	[formation] [int] NOT NULL,
	[dateinscription] [datetime] NULL,
 CONSTRAINT [pk_inscriptions] PRIMARY KEY CLUSTERED 
(
	[stagiaire] ASC,
	[formation] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[inscriptions] ([stagiaire], [formation], [dateinscription]) VALUES (1, 1, CAST(0x00009E5E00000000 AS DateTime))
INSERT [dbo].[inscriptions] ([stagiaire], [formation], [dateinscription]) VALUES (1, 2, CAST(0x00009F8E00000000 AS DateTime))
INSERT [dbo].[inscriptions] ([stagiaire], [formation], [dateinscription]) VALUES (2, 2, CAST(0x00009E5600000000 AS DateTime))
INSERT [dbo].[inscriptions] ([stagiaire], [formation], [dateinscription]) VALUES (2, 3, CAST(0x00009E5600000000 AS DateTime))
/****** Object:  Table [dbo].[FICHES]    Script Date: 05/29/2016 22:20:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[FICHES](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[AVIS_GENERAL] [varchar](2000) NULL,
	[ID_STAGIAIRE] [int] NOT NULL,
	[ID_FORMATION] [int] NOT NULL,
 CONSTRAINT [PK_FICHES] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[DETAIL_FICHES]    Script Date: 05/29/2016 22:20:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[DETAIL_FICHES](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[LIBELLE] [varchar](80) NOT NULL,
	[REMARQUE] [varchar](2000) NOT NULL,
	[ID_FICHE] [int] NOT NULL,
 CONSTRAINT [PK_DETAIL_FICHES] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ELEMENTS]    Script Date: 05/29/2016 22:20:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ELEMENTS](
	[ID] [int] NOT NULL,
	[LIBELLE] [varchar](40) NOT NULL,
	[VALEUR] [varchar](2) NULL,
	[ID_DETAIL] [int] NOT NULL,
 CONSTRAINT [PK_ELEMENTS] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  ForeignKey [FK_DETAIL_FICHES_FICHES]    Script Date: 05/29/2016 22:20:11 ******/
ALTER TABLE [dbo].[DETAIL_FICHES]  WITH CHECK ADD  CONSTRAINT [FK_DETAIL_FICHES_FICHES] FOREIGN KEY([ID_FICHE])
REFERENCES [dbo].[FICHES] ([ID])
GO
ALTER TABLE [dbo].[DETAIL_FICHES] CHECK CONSTRAINT [FK_DETAIL_FICHES_FICHES]
GO
/****** Object:  ForeignKey [FK_ELEMENTS_DETAIL_FICHES]    Script Date: 05/29/2016 22:20:11 ******/
ALTER TABLE [dbo].[ELEMENTS]  WITH CHECK ADD  CONSTRAINT [FK_ELEMENTS_DETAIL_FICHES] FOREIGN KEY([ID_DETAIL])
REFERENCES [dbo].[DETAIL_FICHES] ([ID])
GO
ALTER TABLE [dbo].[ELEMENTS] CHECK CONSTRAINT [FK_ELEMENTS_DETAIL_FICHES]
GO
/****** Object:  ForeignKey [FK_FICHES_formations]    Script Date: 05/29/2016 22:20:11 ******/
ALTER TABLE [dbo].[FICHES]  WITH CHECK ADD  CONSTRAINT [FK_FICHES_formations] FOREIGN KEY([ID_FORMATION])
REFERENCES [dbo].[formations] ([id])
GO
ALTER TABLE [dbo].[FICHES] CHECK CONSTRAINT [FK_FICHES_formations]
GO
/****** Object:  ForeignKey [FK_FICHES_stagiaires]    Script Date: 05/29/2016 22:20:11 ******/
ALTER TABLE [dbo].[FICHES]  WITH CHECK ADD  CONSTRAINT [FK_FICHES_stagiaires] FOREIGN KEY([ID_STAGIAIRE])
REFERENCES [dbo].[stagiaires] ([id])
GO
ALTER TABLE [dbo].[FICHES] CHECK CONSTRAINT [FK_FICHES_stagiaires]
GO
/****** Object:  ForeignKey [fk_inscriptions_formations]    Script Date: 05/29/2016 22:20:11 ******/
ALTER TABLE [dbo].[inscriptions]  WITH CHECK ADD  CONSTRAINT [fk_inscriptions_formations] FOREIGN KEY([formation])
REFERENCES [dbo].[formations] ([id])
GO
ALTER TABLE [dbo].[inscriptions] CHECK CONSTRAINT [fk_inscriptions_formations]
GO
/****** Object:  ForeignKey [fk_inscriptions_stagiaires]    Script Date: 05/29/2016 22:20:11 ******/
ALTER TABLE [dbo].[inscriptions]  WITH CHECK ADD  CONSTRAINT [fk_inscriptions_stagiaires] FOREIGN KEY([stagiaire])
REFERENCES [dbo].[stagiaires] ([id])
GO
ALTER TABLE [dbo].[inscriptions] CHECK CONSTRAINT [fk_inscriptions_stagiaires]
GO
