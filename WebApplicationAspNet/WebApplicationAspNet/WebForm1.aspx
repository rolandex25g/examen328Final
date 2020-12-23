<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="WebForm1.aspx.cs" Inherits="WebApplicationAspNet.WebForm1" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <asp:Label ID="Label1" runat="server" Text="Semestre"></asp:Label>
            <asp:TextBox ID="TextBox1" runat="server"></asp:TextBox>
            <asp:Button ID="Button1" runat="server" Text="Buscar" OnClick="Button1_Click" />
            <hr>
            <br>
            <asp:ListBox ID="ListBox1" runat="server" Width="373px" OnSelectedIndexChanged="ListBox1_SelectedIndexChanged" Height="110px"></asp:ListBox>
            <asp:Button ID="Button3" runat="server" Text="Editar" OnClick="Button3_Click" />
            <hr>
            <br>
            <asp:Label ID="Label2" runat="server" Text="idMateria"></asp:Label>
            <asp:TextBox ID="TextBox2" runat="server"></asp:TextBox><br>
            <asp:Label ID="Label3" runat="server" Text="sigla"></asp:Label>
            <asp:TextBox ID="TextBox3" runat="server"></asp:TextBox><br>
            <asp:Label ID="Label4" runat="server" Text="nombre_materia"></asp:Label>
            <asp:TextBox ID="TextBox4" runat="server" Width="321px"></asp:TextBox><br>
            <asp:Label ID="Label5" runat="server" Text="semestre"></asp:Label>
            <asp:TextBox ID="TextBox5" runat="server"></asp:TextBox><br>
            <hr>
            <br>
            <asp:Button ID="Button2" runat="server" Text="Guardar" OnClick="Button2_Click" />
        
        </div>
        <asp:Label ID="Label6" runat="server" Text="."></asp:Label>
        
        <hr>
        <asp:Label ID="Label7" runat="server" Text="Sigla"></asp:Label>
        <asp:TextBox ID="TextBox6" runat="server"></asp:TextBox><br>
        <asp:Label ID="Label8" runat="server" Text="Nombre materia"></asp:Label>
        <asp:TextBox ID="TextBox7" runat="server"></asp:TextBox><br>
        <asp:Button ID="Button4" runat="server" Text="Guardar Nuevo" OnClick="Button4_Click" />
        <br>
    </form>
    
</body>
</html>
