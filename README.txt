Despa Diana-Alexandra 321CA

Tema 1 - Radix Tree

	Am dezvoltat tema pe baza scheletului de cod atasat enuntului, astfel, clasa Index este cea care contine metoda main.
	
Main
	In metoda main, se citesc pe rand cuvintele din fisierele transmise ca parametri. Cele din primul fisier se insereaza in arbore, conform enuntului si tinandu-se cont de membrul static wordIndex al clasei, care pastreaza numarul de ordine al fiecarui cuvant in text. Prefixele din al doilea fisier sunt cautate, iar indicii frunzelor care apartin cuvintelor cu un anumit prefix sunt inserati in lista de solutii, definita ca membru static al clasei Index cu numele solutions.	
	Pentru rezolvarea temei, am implementat clasele Node, RadixTree si StringOperations.

StringOperations	
	Clasa StringOperations contine o singura metoda care intoarce cel mai lung prefix comun pentru doua string-uri date ca parametri.
	
Node
	Am fososit clasa Node pentru a reprezenta nodurile arborelui. Ea cuprinde doi membri: info - care pastreaza informatia din nod, nextNode - referinta la subarborele subordonat nodului respectiv. Pe langa constructorii de clasa, am implementat un getter si un setter pentru membrul info.
	
RadixTree
	Clasa RadixTree este cea mai complexa, intrucat metodele ei se ocupa de prelucrarea cuvintelor introduse si gasirea indicilor cuvintelor cu un anumit prefix. Clasa agrega o lista de noduri, care reprezinta cuvintele ce au in comun un prefix, aflat pe un nivel superior in arbore. 
	
	-insertWord
	Metoda insertWord cauta pe nivelul curent nodul care are un prefix comun cu parametrul word. Daca un astfel de nod nu este gasit, se adauga cuvantul pe acest nivel, avand ca unic fiu numarul de ordine din text, retinut in Index.wordIndex. Daca se gaseste nodul cautat, cuvantul de inserat este separat de prefixul comun, iar restul este inserat incepand cu urmatorul nivel, printre descendentii nodului gasit. Daca prefixul comun nu coincide cu informatia din nodul gasit, atunci si acesta este separat de prefixul, el substituind informatia initiala, iar restul ramas reprezentand radacina unui nou subarbore, care mosteneste copii nodului initial. Cu alte cuvinte, nodul gasit se sparge pe doua nivele, primul fiind prefixul comun, iar cel de-al doilea - restul cuvantului.

	-checkPrefix
	Metoda checkPrefix, la fel ca si insertWord, cauta la inceput nodul care are un prefix comun cu parametrul dat. Daca un astfel de nod este gasit se verifica daca lungimea si continutul lui coincid. Daca lungimea coincide, dar continutul difera, inseamna ca nu are sens sa continui cautarea. Altfel, se cauta pe urmatorul nivel restul cuvantului ramas dupa inlaturarea prefixului. Daca string-ul cautat coincide cu prefixul comun cu nodul curent, inseamna ca s-a terminat cautarea si trebuie sa obtin indicii cuvintelor care se formeaza mai departe de la acest nod.

	-getTerminals
	Metoda getTerminals adauga la lista de solutii indicii cuvintelor care se formeaza de la radacina tree, transmisa ca parametru. Se itereaza printre nodurile de la nivelul curent si se apeleaza recursiv functia. Daca se gaseste o frunza, continutul ei - indexul, este adaugat la Index.solutions.


Indexarea cuvintelor
	Cum am spus si mai sus, indicii cuvintelor fac parte din arbore, ei reprezentand nodurile terminale sau frunzele sale. La insertia fiecarui cuvant, acestuia i se atribuie un nod fiu, care are in campul info indexul convertit la tipul string, dupa care acest index este incrementat. In metoda getTerminals, unde sunt cautate frunzele arborelui, indicii sunt convertiti inapoi la tipul intreg, pentru a putea fi adaugati in vectorul de solutii.

Testare
	Pe parcursul dezvoltarii temei, am realizat testarea metodelor folosind afisari intermediare intre diferite operatii. Astfel am observat mai bine cum lucreaza programul, unde apar nereguli si ce trebuie modificat.