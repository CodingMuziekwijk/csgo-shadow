<H1>Design Document Csgo-Shadow</H1>
<p>Dit document is niet definitief en zal gedurende het proces worden aangepast.</p>

<H2>MVP</H2>

<p>Als minimal viable product zal er een applicatie worden opgeleverd met alle functionaliteiten zoals hieronder en in het README.MD beschreven,
met uitzondering van de Eco en Bombtimer. Dit vooral omdat de beide niet super complex zijn om te programmeren. Ook is de shadow functie
een origineler en complexer idee in verhouding met de andere twee functies.</p>

<p>De shadow functie moet in staat zijn om vanaf elke loopbare locatie in een map, een shadow te laten lopen. Deze shadow moet zich houden
aan de loopregels waaraan speler zich ook houden. Dit is dus inclusief alle vrijheden en beperkingen (dus niet door muren, 
maar er wel overheen waar dat kan).</p>

<H2>GAP</H2>
<p>Het gap wat de app zal vullen, is (CS:GO) spelers die vroegtijdig uitgeschakeld zijn in een ronde. 
Toch de kans geven om nut te hebben voor het team, doormiddel van informatie verzamelen (en dit doorgeven 
via skype of teamspeak aan je team).</p>

<H2>Risco's</H2>
<p>Doordat er in veel maps paden over elkaar heenlopen of plekken zijn waar je vanaf een kant wel door kan, maar vanaf de andere niet.
Zullen daar enkele maatwerk oplossingen voor moeten komen, die momenteel nog niet allemaal bedacht zijn, of zelfs in kaartgebracht zijn.
Hierdoor ontstaat de kans dat er concessies gedaan moeten worden op naukeurigheid van het eindproduct. Het risco dat deze situatie daadwerkelijk
invloed negatieve invloed heeft is niet heel groot, maar is er wel.</p>

<H2>Classes and methods</H2>

<H4>Activities:</H4>
<ul>
  <li>OpeningscreenActivity</li>
  <li>FactionActivity</li>
  <li>SessionActivity</li>
</ul>

<H4>Classes & Fragments:</H4>
<ul>
  <li>gameMap (Class)</li>
  <li>Shadow (Fragment)</li>
  <li>BombTimer (Fragment)</li>
  <li>Eco (Fragment)</li>
</ul>

<H4>OpeningscreenActivity:</H4>
<p>Hier wordt de map geselecteerd waarin het spel zich plaats vindt.</p>

<H6>Global Variables</H6>
<ul>
    <li>List<gameMap> myMaps</li>
</ul>

<H6>Methods</H6>
<ul>
    <li>private void populateMapList();</li>
    <li>private void populateListView();</li>
    <li>private void registerClickCallBack();</li>
</ul>

<H4>FactionActivity:</H4>
<p>Hier defineer je doormiddel van simpele knoppen, bij welke faction je begint met spelen. 
(aangezien na 15 rondes de fanctions omdraaien)</p>

<H6>Global Variables</H6>
<ul>
    <li>private Bundle bundle;</li>
    <li>private int myMapId;</li>
</ul>

<H6>Methods</H6>
<ul>
    <li>private void onTerrorClick();</li>
    <li>private void onCounterClick();</li>
</ul>

<H4>SessionActivity:</H4>
<p>Dit zal het belangrijkste scherm worden. De verzameling van verschillende fragmenten wordt hier afgebeeld.</p>

<H6>Global Variables</H6>
<ul>
    <li>private Bundle bundle;</li>
    <li>private int myMapId;</li>
    <li>private Bitmap shadowMap;</li>
</ul>

<H6>Methods</H6>
<ul>
    <li>public void setMapOverview(int myMapId);  (Creates shadowFragment)</li>
    <li>Private void SetBombTimer();  (Creates BombtimerFragment</li>
    <li>Private void SetEco();  (Creates EcoFragment</li>
</ul>

<H4>Shadow (Fragment):</H4>
<p>In dit fragment vindt de shadow functie plaats. De output moet de gebruiker een naukeurig beeld geven over waar een tegenstander zich
kan bevinden, zodat deze je minder snel kan verassen door in je rug te schieten. Dit zal grotendeels gerealiseerd worden met een "shadowmap"
deze kaart zal worden gemaakt door het ontwikkelteam en zal met pixelkleuren looproutes en muren aangeven.</p>

<H6>Global Variables</H6>
<ul>
    <li>private ImageView mapOverview;</li>
    <li>private int myMapId;</li>
    <li>private Bitmap bitmap;</li>
    <li>private TextView testTextView;</li>
</ul>

<H6>Methods</H6>
<ul>
    <li>public void setMapOverview(int myMapId);</li>
    <li>public void startShadow(int x, int y);</li>
    <li>public void setMapOverview(int myMapId);</li>
    <li>public Boolean isPath(int pixelColor);</li>
    <li>public Boolean isWall(int pixelColor);</li>
    <li>public Boolean isStair(int pixelColor);</li>
    <li>public Boolean isFinished();</li>
</ul>

<H4>BombTimer (Fragment):</H4>
<p>Dit fragment geeft de gebruiker een mogelijkheid om het starten van een bom klok aan te geven. Vervolgens geeft het als output, 
hoeveel tijd er nog op de klok staat en of het nog zin heeft om te starten met difusen (kost 5 of 10 seconden). </p>

<H6>Global Variables</H6>
<ul>
    <li>None.</li>
</ul>

<H6>Methods</H6>
<ul>
    <li>private void startBombTimer();</li>
    <li>private void resetBombTimer();</li>
    <li>private int getTimeLeftOnTimer();</li>
    <li>private Boolean timerIsFinished();</li>
</ul>

<H4>Eco (Fragment):</H4>
<p>Dit fragment doet een voorspelling op de hoeveelheid geld die je tegenstander bezit. Als input moeten hier twee keuzes komen; simple, advanced.
afhankelijk van de door de gebruiker gekozen invoer methode kunnen er zaken als gewonnen rondes en gekochte items aangegeven worden. Het fragment
doet daarna een schatting op hoeveel geld te tegenstander zou hebben en of zij dus genoeg hebben om wapens te kopen of niet.</p>

<H6>Global Variables</H6>
<ul>
    <li>Private int terrorMoney;</li>
    <li>Private int counterMoney;</li>
</ul>

<H6>Methods</H6>
<ul>
    <li>private Boolean isTerrorEco(int terrorMoney);</li>
    <li>private Boolean isCounterEco(int counterMoney);</li>
    <li>private void addRoundInfo(Boolean roundWon, Boolean enemyEco);</li>
</ul>

<H4>gameMap (Class):</H4>
<p>Deze class bevat de verschillende kaarten die bij de verschillende maps horen. De informatie hierin zal grotendeels worden 
geraadpleegd door de shadow-functie.</p>

<H6>Global Variables</H6>
<ul>
    <li>private String name;</li>
    <li>private int iconID;</li>
    <li>private int overViewID;</li>
    <li>private int shadowMapID</li>
    <li>private bitmap shadowBitmap;</li>
</ul>

<H6>Methods</H6>
<ul>
    <li>public String getName();</li>
    <li>public int getIconID();</li>
    <li>public int getOverViewID();</li>
    <li>public int getShadowMapID();</li>
    <li>Public int getshadowBitmap();</li>
</ul>

<H2>Mockup</H2>
![Figure 1-1](https://github.com/CodingMuziekwijk/csgo-shadow/blob/master/doc/screener_20160108(014423).png)
![Figure 1-1](https://github.com/CodingMuziekwijk/csgo-shadow/blob/master/doc/screener_20160108(014614).png)
![Figure 1-1](https://github.com/CodingMuziekwijk/csgo-shadow/blob/master/doc/screener_20160108(014546).png)

<H2>API's</H2>

None.

<H2>Database's</H2>

None.
