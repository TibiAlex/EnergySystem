# Proiect Energy System Etapa 2

## Despre

Proiectare Orientata pe Obiecte, Seriile CA, CD
2020-2021

<https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa2>

Student: Buzera Tiberiu, Grupa 323CA

## Rulare teste

Clasa Test#main
  * ruleaza solutia pe testele din checker/, comparand rezultatele cu cele de referinta
  * ruleaza checkstyle

Detalii despre teste: checker/README

Biblioteci necesare pentru implementare:
* Jackson Core 
* Jackson Databind 
* Jackson Annotations

Tutorial Jackson JSON: 
<https://ocw.cs.pub.ro/courses/poo-ca-cd/laboratoare/tutorial-json-jackson>

## Implementare

### Entitati

--In package-ul inputdata se gasesc 8 clase , folosite pentru a citi datele din JSON-uri.
Clasa Input citeste datele din fisiere si le parseaza printre celelalte clase,
in InitData si MonthlyUpdate sunt puse datele despre consumatori, distribuitori,
producatori si schimbarile lunare suferite de acestia. 
Cele 3 clase Consumers, Distributors, Producers retin informatii despre fiecare consumator, 
distribuitor si producator in parte.
Iar clasele DistributorChanges si ProducerChanges retin datele ce se vor schimba in fiecare 
luna.

--In package-ul outputdata se afla 5 clase, clasa Output care contine cele 3 Array-uri despre 
consumatorii, producatorii si distribuitorii rezultati in urma simularii. 
Fiecare dintre cele 3 clase ConsumersOutput, DistributorsOutput si ProducersOutput 
contin datele despre consumatorii , distribuitorii si producatorii prelucrati.
Clasa Contract contine date despre consumatorii ce inca mai au contract cu distribuitorii.
Rolul acestor clase este de a afisa datele de iesire in fisierele JSON.

--In package-ul altereddata se afla 4 clase care au rolul principal de a prelucra datele 
despre consumatori, producatori si distribuitori, ConsumersAltered ,DistributorsAltered, 
ProducersAltered contin campurile consumatorilor, distribuitorilor si producatorilor si 
metodele aferente acestora. Clasa MonthyStats este folosita pentru a retine distribuitorii 
lunari ce cumpara energie de la un anumit producator.

--In package-ul factory se gasesc 6 clase si o interfata, iar acestea se ocupa de initializarea 
Array-urilor de consumatori, distribuitori si producatori ce urmeaza sa fie prelucrate si
Array-urile de consumatori, distribuitori si producatori ce urmeaza sa fie afisate.Cele 6 
clase sunt : InitConsAltered, InitDistAltered, InitProdAltered, OutputConsAltered, 
OutputDistAltered, OutputProdAltered, iar numele interfetei este Factory intrucat
aceasta implementeaza un factoty method.

--In package-ul strategy se gasesc 4 clase si o interfata si au rolul de a alege strategia 
potrivita pentru distribuitor. Clasele StrategyGreen,StrategyPrice si StrategyQuantity contin 
metodele necesare pentru strategia lor, clasa Context alege ce metoda trebuie sa foloseasca 
distribuitorul, iar interfata IStrategy implementeaza un strategy pattern.

--In package-ul observer se gasesc 2 interfete, IObserver si IObservable, folosite pentru 
a observa schimbarile producatorilor si atentionarea distribuitorilor.

--Clasa EnergyType din package-ul entities contine tipurile de energie si daca acestea sunt 
sau nu regenerabile.

--Clasa EnergyChoiceStrategyType din package-ul strategies contine tipurile de strategie.

--Clasa SetChanges din package-ul updatedata se ocupa cu modificarea consumatorilor , 
distribuitorilor si a producatorilor in functie de schimbarile lunare continute in MonthlyUpdate 
din package-ul inputdata.

--Clasa method se ocupa cu calcularea si gasirea celui mai ieftin contract in luna respectiva.


### Flow

--La inceputul programului retinem in variabila input datele de intrare din fisierul JSON.
Retinem datele depre consumatori , distribuitori si producatori in 3 Array-uri pentru a fi mai 
usor de prelucrat.

--Inainte de prima luna distribuitorii isi aleg producatorii in functie de strategie.
Apoi fiecare distribuitor isi calculeaza pretul contractului si cautam cel mai ieftin contract.
La inceput toti consumatori vor fi legati la acel contract cu cel mai mic pret.
Apoi atat consumatorii cat si distribuitorii platesc si isi recalculeaza bugetul.

--Apoi intram in simularea propriu-zisa care are loc in numberOfTurns pasi.
Primul lucru pe care il facem in simulare este sa modificam datele distribuitorilor si sa 
recalculam pretul contractelor si sa gasim din nou cel mai ieftin contract.
Adaugam dupa consumatorii noi in Array-ul de consumatori, consumatorii care tocmai au fost adaugati
si cei carora le-a expirat contractul isi cauta un nou distribuitor.
Consumatorii ii platesc pe distribuitori, iar consumatorii faliti sunt scosi de la distribuitorul 
unde aveau contract.
Distribuitorii platesc si ei producatorii recalculandu-si bugetul.
Sunt scosi distribuitorii ce au dat faliment de la producatori.
Producatorii isi updateaza valorile, sunt scosi distribuitorii de la producatorii care s-au 
schimbat, iar acei dist isi cauta alti producatori.
Updatam datele cu contracte lunare ale producatorilor.
Verificam daca mai este vreun distribuitor in joc , daca nu mai este se termina simularea.

--Copiem datele prelucrate in alte 3 Array-uri pentru a afisa datele.
--Cu variabila output afisam in fisierul JSON datele prelucrate.

--Cea mai mare parte dintre metodele folosite se gasesc in clasele ConsumerAltereds, 
DistributorAltereds si ProducersAltereds,
acestea comunicand intre ele prin parametrii dati in metode, clasa main controleaza flow-ul
programului.

### Elemente de design OOP

Am folosit modularizare in clase si package-uri pentru o mai buna intelegere a codului.
Am folosit constructori pentru crearea obiectelor.
Am folosit campuri private pentru ca acestea sa fie accesate doar in cadrul clasei, 
si am adaugat gettere si settere pentru a fi accesate si din afara clasei.
Am folosit interfete si clase care implementeaza acele interfete si metodele lor.
Am folosit genericitate in anumite metode ce puteau primi date de mai multe tipuri.
Am folosit metode suprascrise spre exemplu toString.
Am folosit clase finale in cazul in care acestea urmau sa fie initializate odata 
si neschimbate restul programului.
Am folosit expresii lambda si forEach pentru a inlocui for-ul in situatii in care 
aveam o singura comanda.

### Design patterns

--Am folosit Singleton Pattern in clasele Method si SetChanges intrucat nu am avut nevoie 
decat de o singura instanta a fiecarei clasa pentru intreg programul.
Instanta pentru Method pentru a apela metoda ce calculeaza pretul contractului distribuitorilor
si cel mai ieftin contract si
instanta pentru SetChanges pentru a face modificarile lunare necesare printre consumatori,
distribuitori si producatori.

--Am folosit Factory Pattern pentru a initializa cele 3 Array-uri de prelucrat si cele 
3 Array-uri de afisat folosind o metoda ce primeste un parametru Object si returneaza tot un
Object (aici am folosit genericitate).

--Am folosit Strategy Pattern pentru face mai usor de inteles modul in care distribuitorii isi 
aleg producatorii in functie de tipul strategiei.Am creat 3 clase fiecare cu metoda pe care o
reprezinta , in functie de variabila producerStrategy distribuitorul apeleaza una dintre 
aceste metode.

--Am folosit Observer Pattern pentru a simplifica relatia producator-distribuitor,astfel 
daca producatorul isi modifica cantitatea de energie pe care o transmite distribuitorilor,
toti clientii sai sunt atentionati ,am implementat metoda notify care este apelata cand se
schimba un producator, ea apeleaza metoda update din fiecare distribuitor client. Am
implementat si metodele add si remove pentru a adauga si scoate distribuitorii din Array-ul 
de clienti ai producatorului.

### Dificultati intalnite, limitari, probleme

Checkstyle-ul a fost problematic, a luat destul de mult sa termin cu el. Principalele 
probleme intalnite cu el au fost la magicNumbers (rezolvat prin declararea unor constante), 
ca main-ul sa aiba sub 150 randuri (rezolvat printr-o mai buna modularizare), inlocuirea 
tutror importurilor de tipul .* cu importurile necesare (rezolvat prin citirea erorilor in
momentul stergerii importului :D).

Alte probleme din timpul facerii proiectului au fost indeplinireatutror cerintelor si 
punerea cerintelor in ordinea necesara.
Dupa ce s-au indeplinit aceste cerinte proiectul a mers foarte rapid.

