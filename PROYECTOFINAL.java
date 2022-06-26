package Simulacion;
import java.util.ArrayList;
public class PROYECTOFINAL
{
	public static void main(String[] args)
	{
		double almacen3 =0, esperaTotalTotal3=0, horasExtras3=0;
		double almacen4 =0, esperaTotalTotal4=0, horasExtras4=0;
		double almacen5 =0, esperaTotalTotal5=0, horasExtras5=0;
		double almacen6 =0, esperaTotalTotal6=0, horasExtras6=0;
		for (int i = 0; i < 60; i++)//simula los 60 turnos
		{
			for (int k = 0; k < 4; k++)
			{
				int camionesEsperando=0, cola=0;
				double entreLlegadas = 0,tiempoServicio=0, horaInicio=0,horaFinal=11, Ocio=0,espera=0, hora =11, minuto =0;
				double esperaTotal=0;
				camionesEsperando = esperando();
				ArrayList<Double> horaLLegadas = new ArrayList<Double>();
				while(hora + minuto < 19.3)//calcula las llegadas estimadas
				{
					entreLlegadas = llegadas();
					if(minuto + entreLlegadas>=0.60)
					{
						minuto = minuto + entreLlegadas - 0.60;
						hora++;
					}
					else
						minuto = minuto + entreLlegadas;
					if(minuto+hora<19.3)
						horaLLegadas.add(hora+minuto);
				}
				hora =11; minuto =0;
				for (int j = 0; j < camionesEsperando; j++)
					horaLLegadas.add(0,hora+minuto);
				System.out.println();
				if(k==0)System.out.println("Equipo de 3");
				else if(k==1)System.out.println("Equipo de 4");
				else if(k==2)System.out.println("Equipo de 5");
				else if(k==3)System.out.println("Equipo de 6");
				System.out.printf("%7s %5s %5s %4s %5s %5s %6s %4s\n","#alegen","llega","inicia","serv","final","OcioE","espera","cola");
				for (int j = 0; j < horaLLegadas.size(); j++)
				{
					horaInicio = (horaLLegadas.get(j)>=horaFinal)
							   ?horaLLegadas.get(j) :horaFinal;
					if(horaLLegadas.get(j)<horaFinal) cola++;
					if(Math.floor(horaInicio)==Math.floor(horaFinal)-1 || Math.floor(horaInicio)-1==Math.floor(horaFinal)) hora++;
					if(k==0) tiempoServicio = servicio3();
					else if(k==1)tiempoServicio = servicio4();
					else if(k==2)tiempoServicio = servicio5();
					else if(k==3)tiempoServicio = servicio6();
					if(Math.floor(horaInicio) == Math.floor(horaLLegadas.get(j)) || (horaInicio-horaLLegadas.get(j)>=1))
					{
						if(horaInicio-horaLLegadas.get(j)==1) espera =1;
						else if(horaInicio-horaLLegadas.get(j)>0.999 && horaInicio-horaLLegadas.get(j)<2)
							espera = 1.60-Math.abs(horaInicio % 1 - horaLLegadas.get(j) % 1);
						else 
							espera=horaInicio-horaLLegadas.get(j);
					}
					else if (horaInicio!=horaLLegadas.get(j))
						espera = 0.6 - Math.abs(horaInicio % 1 - horaLLegadas.get(j) % 1);
					if((horaInicio + tiempoServicio-hora)>0.59)
					{
						minuto = horaInicio + tiempoServicio - 0.60 - hora;
						hora++;
					}
					else
						minuto = horaInicio + tiempoServicio - hora;
					if(Math.floor(horaInicio) == Math.floor(horaFinal))
						Ocio = horaInicio - horaFinal;
					else if (horaInicio != horaFinal)
						Ocio = 0.6 - Math.max(horaInicio % 1, horaFinal % 1) + Math.min(horaInicio % 1, horaFinal % 1);	
					horaFinal=hora+minuto;	
					if(minuto>0.59)
					{
						minuto =0;
						hora++;
					}
					System.out.printf("%13.2f  %5.2f %4.2f %4.2f %5.2f %6.2f %4d\n",horaLLegadas.get(j),horaInicio ,tiempoServicio, horaFinal,Ocio,espera,cola);
					if(horaInicio!=horaLLegadas.get(j))cola--;
					esperaTotal+= espera;
				}
				hora += minuto / 0.6;
				if(k==0)
				{
					horasExtras3 += Math.abs((hora-19.5)*3*37.5);
					almacen3 += hora *500;
					esperaTotalTotal3 += esperaTotal*100;
				}
				else if(k==1)
				{
					horasExtras4 += Math.abs((hora-19.5)*4*37.5);
					almacen4 += hora *500;
					esperaTotalTotal4 += esperaTotal*100;
				}
				else if(k==2)
				{
					horasExtras5 += Math.abs((hora-19.5)*5*37.5);
					almacen5 += hora *500;
					esperaTotalTotal5 += esperaTotal*100;
				}
				else if(k==3)
				{
					horasExtras6 += Math.abs((hora-19.5)*6*37.5);
					almacen6 += hora *500;
					esperaTotalTotal6 += esperaTotal*100;
				}
			}
		}
		System.out.printf("\n%6s %7s %5s %7s %5s %7s\n","Tamaño","salario","extra","extraCamion","almacen","total");
		System.out.printf("%6d %7d %7f %7f %7f %7f\n",3,25*8*3,horasExtras3/60.0,esperaTotalTotal3/60.0,almacen3/60.0,25*8*3+horasExtras3/60.0+esperaTotalTotal3/60.0+almacen3/60.0);
		System.out.printf("%6d %7d %7f %7f %7f %7f\n",4,25*8*4,horasExtras4/60.0,esperaTotalTotal4/60.0,almacen4/60.0,25*8*4+horasExtras4/60.0+esperaTotalTotal4/60.0+almacen4/60.0);
		System.out.printf("%6d %7d %7f %7f %7f %7f\n",5,25*8*5,horasExtras5/60.0,esperaTotalTotal5/60.0,almacen5/60.0,25*8*5+horasExtras5/60.0+esperaTotalTotal5/60.0+almacen5/60.0);
		System.out.printf("%6d %7d %7f %7f %7f %7f\n",6,25*8*6,horasExtras6/60.0,esperaTotalTotal6/60.0,almacen6/60.0,25*8*6+horasExtras6/60.0+esperaTotalTotal6/60.0+almacen6/60.0);
	}
	private static int esperando()
	{
		double num= Math.random();//camiones esperando
		if(num>0.5 && num<=0.75)  return 1;
		else if(num>0.75 && num<=0.90)  return 2;
		else if(num>0.9 && num<=1)  return 3;
		return 0;
	}
	private static double llegadas()
	{
		double num= Math.random();//tiempo entre llegadas
		if(num > 0 && num <= 0.02)
			return 0.20;
		else if(num > 0.02 && num <= 0.10)
			return 0.25;
		else if(num > 0.10 && num <= 0.22)
			return 0.30;
		else if(num > 0.22 && num <= 0.47)
			return 0.35;
		else if(num > 0.47 && num <= 0.67)
			return 0.40;
		else if(num > 0.67 && num <= 0.82)
			return 0.45;
		else if(num > 0.82 && num <= 0.92)
			return 0.50;
		else if(num > 0.92 && num <= 0.97)
			return 0.55;
		else return 0.60;
	}
	private static double servicio3()
	{
		double num= Math.random();//tiempo de servicio
		if(num > 0 && num <= 0.05)
			return 0.20;
		else if(num > 0.05 && num <= 0.15)
			return 0.25;
		else if(num > 0.15 && num <= 0.35)
			return 0.30;
		else if(num > 0.35 && num <= 0.60)
			return 0.35;
		else if(num > 0.60 && num <= 0.72)
			return 0.40;
		else if(num > 0.72 && num <= 0.82)
			return 0.45;
		else if(num > 0.82 && num <= 0.9)
			return 0.50;
		else if(num > 0.9 && num <= 0.96)
			return 0.55;
		else return 0.60;
	}
	private static double servicio4()
	{
		double num= Math.random();//tiempo de servicio
		if(num > 0 && num <= 0.05)
			return 0.15;
		else if(num > 0.05 && num <= 0.20)
			return 0.20;
		else if(num > 0.20 && num <= 0.40)
			return 0.25;
		else if(num > 0.40 && num <= 0.60)
			return 0.30;
		else if(num > 0.60 && num <= 0.75)
			return 0.35;
		else if(num > 0.75 && num <= 0.87)
			return 0.40;
		else if(num > 0.87 && num <= 0.95)
			return 0.45;
		else if(num > 0.95 && num <= 0.99)
			return 0.50;
		else return 0.55;
	}
	private static double servicio5()
	{
		double num= Math.random();//tiempo de servicio
		if(num > 0 && num <= 0.1)
			return 0.10;
		else if(num > 0.1 && num <= 0.28)
			return 0.15;
		else if(num > 0.28 && num <= 0.50)
			return 0.20;
		else if(num > 0.50 && num <= 0.68)
			return 0.25;
		else if(num > 0.68 && num <= 0.78)
			return 0.30;
		else if(num > 0.78 && num <= 0.86)
			return 0.35;
		else if(num > 0.86 && num <= 0.92)
			return 0.40;
		else if(num > 0.92 && num <= 0.97)
			return 0.45;
		else return 0.50;
	}
	private static double servicio6()
	{
		double num= Math.random();//tiempo de servicio
		if(num > 0 && num <= 0.12)
			return 0.05;
		else if(num > 0.12 && num <= 0.27)
			return 0.10;
		else if(num > 0.27 && num <= 0.53)
			return 0.15;
		else if(num > 0.53 && num <= 0.68)
			return 0.20;
		else if(num > 0.68 && num <= 0.80)
			return 0.25;
		else if(num > 0.80 && num <= 0.88)
			return 0.30;
		else if(num > 0.88 && num <= 0.94)
			return 0.35;
		else if(num > 0.94 && num <= 0.98)
			return 0.40;
		else return 0.45;
	}
}