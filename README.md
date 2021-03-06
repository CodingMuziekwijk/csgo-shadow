# csgo-shadow 

<P>Counter Strike Global Offensive (CS:GO) is een computerspel gemaakt door Valve. Dit spel heeft 10 miljoen unieke maandelijkse gebruikers en wordt beschouwd als de meest competitieve shooter. </P>

<P>Een potje wordt gespeeld door twee teams (Terroristen en de Counter-Terroristen) van vijf spelers en is gewonnen zodra een team 16 rondes (van 2 a 3 min) gewonnen heeft. Hierbij heeft elke speler één enkel leven. Als terrorist win je een ronden door een bom af te laten gaan op een specifieke locatie. Als counter-terrorist win je logischerwijs door dit te voorkomen. </P>

<P>Een belangrijke factor in het spel is informatie verzamelen voor je team. Waar bevinden de spelers van het andere team zich? Wat voor wapens dragen ze? Hoeveel geld hebben ze? Enzovoort. </P>

<H1>De Applicatie</H1>

<H3>Functies:</H3>

<ul>
	<li>Shadow (main)</li>
	<li>Detonatie timer (optioneel)</li>
	<li>Eco calculator (optioneel)</li>
</ul>

<H2>Shadow</H2>

<P>Dit is de belangrijkste functie (welke overigens naar mijn weten niet bestaat). Hierbij krijg je op je mobiel / tablet een plattegrond van de omgeving te zien. Wanneer er vijand gedetecteerd word, klik je op deze positie op de kaart. Hierna ontstaat er een soort “virus” vanaf die plek die zich over de kaart verspreid. Dit virus is een visualisatie van de maximaal mogelijke afstand die de speler afgelegd kan hebben. 
Hierdoor kun je vrij nauwkeurig zien of een speler al zorgen moet maken dat de vijand in ze rug komt of toch bij dat andere kamertje terecht is gekomen. </P>

![Figure 1-1](http://i.imgur.com/tusRdlz.jpg)

<H2>Detonatie timer (optioneel)</H2>

<P>Een bom ontploft 45 seconden nadat deze is geactiveerd. 10 seconden daarvoor moet je maken dat je weg bent en het kost 5 of 10 seconden om een bom onschadelijk te maken. De timer zou met een druk op de knop geactiveerd moeten worden, waarna deze afhankelijk van je team relevante informatie moet printen op het scherm (eventueel geluiden).</P>


<H2>Eco calculator (optioneel)</H2>

<P>De uitrusting die je gebruikt tijdens een ronden moet je kopen. Dit kost uiteraard in-game geld en ditzelfde geld verdien je door rondes te winnen en kills te maken. Soms is het handig om als team een ronde niks te kopen, zodat je de ronde erna genoeg hebt om een degelijke uitrusting te betalen. Dit wordt: “Eco” genoemd. Wanneer je van tevoren weet dat een tegenstander een eco ronde neemt kan dit veel voordelen bieden. De calculator zou een voorspelling moeten doen op basis van simpele input.</P>

<H1>Data sets and data sources</H1>

<P>Voor het maken van de app, is de volgende informatie nodig:</P>
<ul>
	<li>Plattegronden</li>
	<li>Spel variabelen</li>
</ul>

<h2>Plattegronden</h2>
<P>De plattegronden zijn op verschillende fora te vinden in verschillende grote en vormgeving. Deze fora zullen hiervoor geraapleegd worden.</P>

<h2>Spel variabelen</h2>
<P>Een aantal variabelen zijn bekend onder de spelers van het spel. Het moeilijkste variabel zal de snelheid worden. Hoewel er bekend is dat iemand die met een mes in ze hand loopt het snelst beweegt en deze waard 210 is in het spel. Is het nog een kleine kunst om dit te vertalen naar een plattegrond. Om dit juist te bepalen zal er onder andere meet proef in het spel gedaan worden.</P>

<H1>Decomposing the problem</H1>
<P>Aangezien de focus ligt op het maken van de shadow functionaliteit en deze ook de ingewikkeldste is, wordt dit opgedeeld in meerdere deel problemen:</P>

<ul>
	<li>Hoe wordt de kaart geimplementeerd zodat deze makkelijk te gebruiken is?</li>
	<li>Wat zijn de bewandelbare paden?</li>
	<li>Hoe leest de app die paden af?</li>
	<li>Hoe bewerkt de app de kaart?</li>
	<li>Hoe visualiseer je de mogelijke verplaatsing?</li>
	<li>Hoe lees je de coordinaten van een scherm aanraking af?</li>
</ul>

<H1>Activities</H1>

<P>Met de huidige kennis lijkt het logisch om een openingsscherm met een listview te hebben. Hier kan vervolgens gekozen worden uit een van de speelbare kaarten/levels. Vervolgens beland je in het informatie scherm, waar zich grofweg alle functies bevinden. Eventueel zou er nog een settings activity bijkomen voor bijvoorbeeld het tweaken van de kleur van de visualisatie. De bomb plant functie zou heel simpel en compact op het informatie scherm geplaats worden. De eco calculator zou eventueel een eigen activity kunnen krijgen wanneer er wordt gekozen voor veel user input. Mocht er voor een simpele versie gekozen worden dan voldoet een kleine rand in het scherm. (zie foto)</P>

![Figure 1-2](http://i.imgur.com/alfA7uO.png)

<H1>Potential problems</H1>

<P>Het verversen van de kaart zou vertraging kunnen krijgen waardoor de informatie niet accuraat is. Ook zou eventueel teweinig/teveel tijd een probleem kunnen zijn, doordat het inschatten van het werklast moeilijk is. Verder zou het telang door gaan met een verkeerde methode om de kaart te bewerken/uit te lezen een tijd probleem kunnen opleveren. Hiervoor is goed contact met de begeleider nodig tijdens het project.</P>

<H1>Vergelijkbare visualisaties / Apps</H1>

<P>Een vergelijkbare app lijkt nog niet te bestaan. Er zijn uiteraard veel apps die de kaarten inbeeld kunnen brengen en informatie over het spel bieden, maar een tool als de shadow-functie moet worden, heb ik afgelopen weken niet kunnen vinden. De visualisatie moet een lijken op dat van de search/pathfind algoritmes waarbij de shadow pixel voor pixel zijn weg verder zoekt.</P>

![Figure 1-3](http://i.imgur.com/yUTS6Id.png)
