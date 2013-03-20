A0B36PR2
========

Semestrální práce PR2

Název práce : Maticová kalkulačka

Zadání práce : Vytvořit program s grafickým uřivatelským prostředím, který bude umožňovat základní operace s maticemi
               (sčítání, násobení, maticové násobení, výpočet determinantu, inversní matice atd.)
               
Řešení : Grafické prostředí řeším pomocí FlowLayout managera. Program neobsahuje žádná "vyskakovací" okna,
         kromě informačních (Matice uložena apd.). Vše (zadání matic, nabídka operací i výsledek) je tedy v jednom okně.
         Hlavní běh programu zajišťují 2 třídy :
          - Matice - reprezentuje matice pomocí pole double a dvou integer pro počet řádků a sloupců. Tato třída
                     bude také zajíšťovat veškeré operace se samotnými maticemi (sčítání, násobení atd.).
          - Seznam - (s vnitřní třídou Prvek) slouží pro uchovávání matic během běhu programu. Navíc obsahuje metody
                     pro ukládání a načítání matic do/ze souboru.
          + Třídy nutné pro běh GUI
