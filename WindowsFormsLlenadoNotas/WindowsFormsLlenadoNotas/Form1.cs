using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

using Npgsql;

namespace WindowsFormsLlenadoNotas
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }
        public class ComboboxItem
        {
            public string Text { get; set; }
            public object Value { get; set; }

            public override string ToString()
            {
                return Text;
            }
        }
        private void button1_Click(object sender, EventArgs e)
        {
            DataTable dataTable = new DataTable("Materias");
            dataTable.Columns.Add("idMateria");
            dataTable.Columns.Add("nombre_materia");

            listBox1.DataSource = dataTable;//Limpiar combo

            textBox4.Text = "";//id
            textBox5.Text = "";//sigla
            textBox6.Text = "";//materia
            textBox7.Text = "";//nota
            label9.Text = "";

            String ci=textBox1.Text;
            String periodo = textBox2.Text;
            String gestion = textBox3.Text;

            NpgsqlConnection con;
            String cadena = "Server=localhost;Port=5432;User Id=postgres;Password=root;Database=academico2;";
            try
            {
                con = new NpgsqlConnection(cadena);
                con.Open();
                String query = "select M.idMateria,M.nombre_materia" +
                " from inscripcion as I, alumno as A, materia as M" +
                " where I.idAlumno=A.idAlumno" +
                " and I.idMateria=M.idMateria" +
                " and A.ci='" + ci + "'" +
                " and I.periodo='" + periodo + "'"+
                " and I.gestion='" + gestion + "'";
                NpgsqlCommand command = new NpgsqlCommand(query, con);

                NpgsqlDataReader dr = command.ExecuteReader();
                while (dr.Read())
                {
                    //res = res + dr[0] + "," + dr[1] + "," + dr[2] + "," + dr[3];
                    dataTable.Rows.Add(Convert.ToInt32(dr[0]), Convert.ToString(dr[1]));
                }
                con.Close();
            }
            catch (Exception)
            {
                con = null;
            }

            listBox1.DataSource = dataTable;
            listBox1.DisplayMember = "nombre_materia";
            listBox1.ValueMember = "idMateria";
            listBox1.Refresh();
            //listBox1.SelectedIndex = 0;

        }

        private void listBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            DataRow selectedDataRow = ((DataRowView)listBox1.SelectedItem).Row;
            String idMateria = Convert.ToString(selectedDataRow["idMateria"]);

            String ci = textBox1.Text;
            String periodo = textBox2.Text;
            String gestion = textBox3.Text;
            NpgsqlConnection con;
            String cadena = "Server=localhost;Port=5432;User Id=postgres;Password=root;Database=academico2;";
            try
            {
                con = new NpgsqlConnection(cadena);
                con.Open();
                String query = "select I.idInscripcion,M.sigla,M.nombre_materia,I.nota" +
                " from inscripcion as I, alumno as A, materia as M" +
                " where I.idAlumno=A.idAlumno" +
                " and I.idMateria=M.idMateria" +
                " and A.ci='" + ci + "'" +
                " and I.periodo='" + periodo + "'" +
                " and I.gestion='" + gestion + "'" +
                " and I.idMateria='" + idMateria + "'";
                NpgsqlCommand command = new NpgsqlCommand(query, con);

                NpgsqlDataReader dr = command.ExecuteReader();
                while (dr.Read())
                {
                    textBox4.Text = Convert.ToString(dr[0]);//idInscripcion
                    textBox5.Text = Convert.ToString(dr[1]);//sigla
                    textBox6.Text = Convert.ToString(dr[2]);//materia
                    textBox7.Text = Convert.ToString(dr[3]);//nota
                }
                con.Close();
                label9.Text = "";
            }
            catch (Exception)
            {
                con = null;
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            String idInscripcion = textBox4.Text;
            String nota = textBox7.Text;
            NpgsqlConnection con;
            String cadena = "Server=localhost;Port=5432;User Id=postgres;Password=root;Database=academico2;";
            if (idInscripcion.Length >0) {
                try
                {
                    con = new NpgsqlConnection(cadena);
                    con.Open();
                    String query = "update inscripcion set" +
                    " nota='" + nota + "'" +
                    " where idInscripcion='" + idInscripcion + "'";

                    NpgsqlCommand command = new NpgsqlCommand(query, con);
                    command.ExecuteNonQuery();
                    con.Close();

                    label9.Text = "Registro guardado correctamente";
                }
                catch (Exception)
                {
                    con = null;

                }
            }
        }
    }
}
