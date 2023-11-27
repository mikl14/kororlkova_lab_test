<h1 align="center">Лаба по технологии ЦУП 
</h1>
<h3 align="center"><img src="https://bestanimations.com/media/rockets/1701913261this-is-ricket-science-tect-rocket-docking-animated-gif-image.gif" height="300"/></h3>

<h2>Структура классов</h2>
<h3>Пакет Config:</h3>

**Config** - класс содержащий данные конфигурации прочитаные из файла конфигурации
<ul>
     <br> **Методы**: <br>  <br>
      <ul>
          read - читает XML файл конфигурации  <br> <br>
          print - выводит в консоль данные  <br> 
     </ul>
</ul>
<h3>Пакет Datas:</h3> 

Telemetry_data - Класс содержащий информацию об одном приборе из файла XML <br> 
  <ul>
   <p>     Поля: </p> 
      <ul>
        String Param_name - имя параметра (прибора) <br>  <br>
        int Param_number - рабочий номер прибора <br> <br>
        String[] Textes_datas - Строки лежащие в теге Textes в файле <br> <br>
        String Description - Описание <br> <br>
        </ul>
   </ul>

Data_Buffer - Класс содержащий структуры с данными со всех файлов, и методы работы с ними
<ul> 
 <br> Статические поля: <br> <br>
  <ul>
      XML_datas - HashMap с ключем int хранящий рабочий номер прибора, в значении объект типа Telemetry_data <br> <br>
      ION_datas - HashMap с ключем int хранящий рабочий номер прибора, в значении объект типа String <br> <br>
      Record_datas  - HashMap с ключем int хранящий рабочий номер прибора, в значении объект типа List<String> <br> <br>
    </ul>
 <br> Методы: <br> <br>
      <ul>
      findXMlbyName - метод поиска прибора в XML записях по имени, возвращает номер прибора <br> <br>
      FindByList - Принимает на вход списки с номерами приборов и их именами, выполняет поиск по всем записям и возвращает список записей с данными выюраных приборов. <br> <br>
      mapToList - Преобразовывает Map<Integer, Telemetry_data> и возвращает List<Telemetry_data> сортированный в алфавитном порядке ( нужен для вывода интерфейса ) <br> <br>
      </ul>
  </ul>
Data_Reader - Класс содержащий методы для чтения всех 3х типов файлов. XML, TMI(бинарные файлы с заданной структурой), Dim 
  <ul>  
    <br> Методы: <br> <br>
      <ul>
       ParceTm - Метод читающий бинарный файл и формирующий объект записи через Tm_dataBuilder и записывающий данные в Databuf - Record_datas <br> <br>
       Read_Demention - Метод читающий файлы Dim и записывающий данные в Databuf - ION_datas <br> <br>
       Xml_Read_data - Метод читающий файлы XML и записывающий данные в Databuf - Xml_datas <br> <br>
      </ul>
    </ul>

<h3> Пакет Tm_dat: </h3>
    <ul>
    Tm_dataBuilder - Билдер для объектов наследников DataRecord <br> <br>
    DataRecord - Класс прородитель для смысловых записей наследник TM_base <br> <br>
    TM_base - Абстрактный класс прородитель для смысловых и служебных записей <br> <br>
    Tm_message - класс служебных записей <br> <br>
    Tm_Code,Tm_Double,Tm_Long,Tm_Point - Записи разных типов <br> <br>
    </ul>
    



