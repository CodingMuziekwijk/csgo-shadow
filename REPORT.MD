#Report

![Figure 1-1](https://github.com/CodingMuziekwijk/csgo-shadow/blob/master/doc/00ScreenOverView00.jpg)

<h3>Wat is Counter Strike?</h3>
<P>Counter Strike Global Offensive (CS:GO) is een computerspel gemaakt door Valve. Dit spel heeft 10 miljoen unieke maandelijkse gebruikers en wordt beschouwd als de meest competitieve shooter. </P>

<P>Een potje wordt gespeeld door twee teams (Terroristen en de Counter-Terroristen) van vijf spelers en is gewonnen zodra een team 16 rondes (van 2 a 3 min) gewonnen heeft. Hierbij heeft elke speler één enkel leven. Als terrorist win je een ronden door een bom af te laten gaan op een specifieke locatie. Als counter-terrorist win je logischerwijs door dit te voorkomen. </P>

<P>Een belangrijke factor in het spel is informatie verzamelen voor je team. Waar bevinden de spelers van het andere team zich? Wat voor wapens dragen ze? Hoeveel geld hebben ze? Enzovoort. </P>

<h3>Introductie: CS:GO Shadow App</h3>
<P>De app is gericht op mensen die de competatieve spel variant in CS:GO spelen en dan in het specifiek de spelers die als eerste dood gaan in een potje. De app geeft deze mensen de mogelijkheid om nuttige informatie te verzamelen voor de (nog levende) teamgenoten.</P>

<P>CS:GO Shadow biedt hierbij drie core functionaliteiten die deze spelers moeten ondersteunen in het verzamelen van informatie:</P>
<ul>
	<li>Shadow</li>
	<li>Eco</li>
	<li>BombTimer</li>
</ul>

![Figure 1-2](https://github.com/CodingMuziekwijk/csgo-shadow/blob/master/doc/00FragmentOverView00.jpg)

<P>Shadow is een tool waarbij de speler op het scherm kan klikken om de locatie van een gevonden vijand te markeren. Vervolgens gaat er een "shadow" lopen vanaf deze locatie. De shadow markeert de locaties waar de vijand zich kan bevinden (door de kaart over te lopen met de maximale snelheid die een speler kan hebben).</P>

<P>Eco is een tool die (grofweg) bijhoudt hoeveel geld een vijandig team bezit. Hierdoor kan voorspelt worden of de vijanden genoeg geld hebben om wapens te kopen of niet.</P>

<P>BombTimer is een simpele (maar doch nuttige) tool waar met een simpele klik op een knop een bomb timer gestart kan worden. Aangezien het nauwkeurig bijhouden van deze tijd niet in het originele spel zit.</P>

<H1>Technisch Design</H1>

<H2>Overzicht</H2>

![Figure 1-3](https://github.com/CodingMuziekwijk/csgo-shadow/blob/master/doc/00ActivityVariablePassingOverView00.jpg)

![Figure 1-4](https://github.com/CodingMuziekwijk/csgo-shadow/blob/master/doc/00FragmentVariableOverView00.jpg)


<H2>Activities</H2>

<H3>OpeningScreenActivity</H3>

<H4>GlobalVariables</H4>

<ul>
	<li>private List myMaps = new ArrayList();</li>
</ul>

<H4>Methods & Functions</H4>

<ul>
	<li>private void populateMapList()</li>
	<li>private void populateListView()</li>
	<li>private class MyListAdapter extends ArrayAdapter</li>
	<li>private void registerClickCallBack()</li>
</ul>

<H3>FactionActivity</H3>

<H4>GlobalVariables</H4>

<ul>
	<li>private Bundle bundle;</li>
	<li>private int myMapId;</li>
	<li>private int myShadowMapId;</li>
	<li>private boolean startFactionIsTerror;)</li>
</ul>

<H4>Methods & Functions</H4>

<ul>
	<li>private void createsTerrorButtons()</li>
	<li>private void createsCounterButtons()</li>
	<li>private void createIntent()</li>
</ul>

<H3>SessionActivity</H3>

<H4>GlobalVariables</H4>

<ul>
	<li>private int myMapId;</li>
	<li>private int myShadowMapId;</li>
	<li>private boolean startFactionIsTerror;</li>
</ul>

<H4>Methods & Functions</H4>

<ul>
	<li>public void setMapOverview(int myMapId)</li>
	<li>public void setShadowBitmap(int myShadowMapId)</li>
	<li>public void setFaction(boolean startFactionIsTerror)</li>
	<li>public void resetTimer()</li>
</ul>

<H2>Classes</H2>

<H3>gameMap</H3>

<P>Initialize conditions: public gameMap(String name, int iconID, int overViewID, int shadowOverViewID)</P>

<H4>GlobalVariables</H4>

<ul>
	<li>private String name;</li>
	<li>private int iconID;</li>
	<li>private int overViewID;</li>
	<li>private int shadowOverViewID;</li>
</ul>

<H4>Methods & Functions</H4>

<ul>
	<li>public String getName()</li>
	<li>public int getIconID()</li>
	<li>public int getOverViewID()</li>
	<li>public int getShadowOverViewID()</li>
</ul>

<H3>Eco</H3>

<P>Initialize conditions: public Eco(boolean terror_faction)</P>

<H4>GlobalVariables</H4>

<ul>
	<li>private int counterMoney = 800;</li>
	<li>private int terrorMoney = 800;</li>
	<li>private boolean terrorFaction;</li>
	<li>private int roundNumber = 1;</li>
	<li>private int winStreak = 0;</li>
	<li>private int loseStreak = 0;</li>
	<li>private String ecoMessage;()</li>
</ul>

<H4>Methods & Functions</H4>

<ul>
	<li>public int getRound_number()</li>
	<li>public String getEcoMessage()</li>
	<li>public String getFactionName()</li>
	<li>public boolean isTerror_faction()</li>
	<li>public void endOfRound(Boolean won, Boolean eco)</li>
	<li>private void resetEco()</li>
	<li>private void isEndOfGame()</li>
	<li>private void isHalfTime()</li>
	<li>private void updateStreak(boolean won)</li>
	<li>private void updateRoundMoney(boolean won)</li>
	<li>public void checkMoneyCap()</li>
	<li>public int calculateEcoPercentage()</li>
	<li>public void ecoHandler(boolean eco)</li>
</ul>

<H3>Shadow</H3>

<P>Initialize conditions: public Shadow(Bitmap shadowMap, Bitmap bitmap)</P>

<H4>GlobalVariables</H4>

<ul>
	<li>int xLocalOrigin;</li>
	<li>int yLocalOrigin;</li>
	<li>Bitmap shadowMap;</li>
	<li>Bitmap bitmap;</li>
	<li>ArrayList xList = new ArrayList();</li>
	<li>ArrayList yList = new ArrayList();</li>
	<li>ArrayList xListContainer = new ArrayList();</li>
	<li>ArrayList yListContainer = new ArrayList();</li>
</ul>

<H4>Methods & Functions</H4>

<ul>
<li>public Bitmap calculateNewFrame()</li>
<li>public void addCordsToList(int x, int y, boolean outer)</li>
<li>public void createCircle(int x, int y)</li>
<li>public void checkCircle(int xOrigin, int yOrigin, int yOffset, int xOffset)</li>
<li>public void checkPixel(int x, int y, boolean outer)</li>
<li>public boolean whiteInMap()</li>
<li>public int getColor(int x, int y)</li>
<li>private Boolean isWhite(int color)</li>
<li>private Boolean isRed(int color)</li>
<li>private Boolean isMagenta(int color)</li>
<li>private Boolean isGreen(int color)</li>
<li>private Boolean isBlue(int color)</li>
<li>private Boolean isGray(int color)</li>
<li>private Boolean isYellow(int color)</li>
<li>private Boolean isCyan(int color)</li>
<li>public void clearCordList()</li>
<li>public void clearCordListContainer()</li>
<li>public void setNewCordList()</li>
<li>private int makeNewPixel(int pixel)</li>
<li>public void makePixelGreen(int x, int y)</li>
</ul>

<H2>Fragments</H2>

<H3>EcoFragment</H3>

<H4>GlobalVariables</H4>

<ul>
	<li>private boolean startFactionIsTerror;</li>
	<li>private TextView roundView;</li>
	<li>private TextView factionView;</li>
	<li>private TextView ecoView;</li>
	<li>private Eco eco;</li>
</ul>

<H4>Methods & Functions</H4>

<ul>
	<li>public void setFaction(boolean myFaction)</li>
	<li>private void updateRoundView()</li>
	<li>private void createButtons(View view)</li>
	<li></li>
</ul>

<H3>BombTimerFragment</H3>

<H4>GlobalVariables</H4>

<ul>
	<li>private TextView countDownText;</li>
	<li>private Button start_countdown_button;</li>
	<li>CountDownTimer countDownTimer;</li>
</ul>

<H4>Methods & Functions</H4>

<ul>
	<li>private void createCountdownButton(View view)</li>
	<li>public void updateTextView( final String message)</li>
	<li>public void resetTimer()</li>
</ul>

<H3>ShadowFragment</H3>

<H4>GlobalVariables</H4>

<ul>
	<li>private ImageView mapOverview;</li>
	<li>private ImageView shadowMapOverview;</li>
	<li>private Bitmap myShadowMapIdBackup;</li>
	<li>private Bitmap myBitMapIdBackup;</li>
	<li>private Bitmap bitmap;</li>
	<li>private Bitmap shadowMap;</li>
	<li>private TextView testTextView;</li>
	<li>final Timer timer = new Timer();</li>
	<li>private TimerTask task;</li>
	<li>private Button resetButton;</li>
	<li>private Shadow shadow;</li>
	<li>private boolean timerOn= false;</li>
	<li>private int mapId;</li>
	<li>private int shadowMapId;mer()</li>
</ul>

<H4>Methods & Functions</H4>

<ul>
	<li>public void setMapOverview(int myMapId)</li>
	<li>public void setShadowBitMap(int myShadowMapId)</li>
	<li>public void updateMapOverview()</li>
	<li>public void clearTextViewOverview()</li>
	<li>public void resetShadow()</li>
	<li>public void createOnTouchListener()</li>
	<li>private void createResetButton(View view)</li>
	<li>private void showResetButton()</li>
	<li>private void createTimerTask()</li>
</ul>

<H1>Uitdagingen en keuzes</H1>

<H3>Hoe verwerk ik de informatie en visualiseer ik dit op een bitmap?</H3>
<p>Voor de eerste probleemstelling had ik 3 verschillende opties bedacht:</p>
<ul>
	<li>Een shadowbitmap maken waarbij ververschillende opstakels een andere kleur hebben. Vervolgens vergelijkt het algoritme de kleuren van de pixel met de regels.</li>
	<li>Meerdere shadowbitmappen maken welke vooral invloed hebben op de verdieping/hoogte van de speler. Hierdoor zijn minder regels nodig dan bij optie 1, maar kost wel meer werk in photoshop</li>
	<li>Voor elke pixel een object "pixel" maken waar informatie in staat over de mogelijke stappen. (dit resulteert waarschijnlijk in veel regels)</li>
</ul>

<P>Uiteindelijk is er gekozen voor de eerste optie, het maken van één enkele shadowbitmap. Dit leek een logische optie, doordat het meer programmeer vaardigheden kost dan optie twee en waarschijnlijk een stuk minder RAM kost dan optie 3.</P>

![Figure 1-5](https://github.com/CodingMuziekwijk/csgo-shadow/blob/master/doc/normalVSshadow.jpg)

<H4>Reflectie</H4>
<P>De keuze voor één shadowbitmap was uiteindelijk niet de juiste voor het perfecte eindresultaat. Het blijkt onmogelijk te zijn om een goed werkend kruispunt te maken met deze methode, mede door het interpoleren wat android automatisch doet op bmps.</P>

<H3>Fragments</H3>

<P>Voor de deelfuncties van de SessionActivity had ik de mogelijkheid om hiervoor fragments te gebruiken. Hoewel dit niet perse nodig was, leek die me wel een goede keuze om verschillende redenen. Het gebruik van fragments was iets wat ik nog niet kon en in het kader van nieuwe dingen leren, had dit dus een pre.
Ook scheen het een belangrijk onderdeel te zijn van android programmeren, doordat je steeds herhalende modules gemakkelijk opnieuw kunt gebruiken ipv. opnieuw moet maken. Ook gaf dit de mogelijkheid om de code wat meer op te splitsen, zodat het overzichtelijker zou worden.</P>

<H4>Reflectie</H4>

<P>Hoewel het soms wel makkelijker geweest was als ik geen fragments gebruikt had. Ben ik er over het algemeen tevreden mee. De verschillende functies zijn duidelijk te onderscheiden en het communiceren met en tussen fragments heb ik ondertussen door.</P>

<H4>Hulpmiddelen</H4>

<P>https://www.youtube.com/watch?v=dQ6uc__qP-g</P>

<H3>isTerror_faction variable</H3>

<P>Om de gekozen faction bij te houden had ik een variabel nodig. Aangezien er twee toestanden waren leek een Boolean logisch. De andere optie zou een String zijn waar vervolgens met een methode een boolean uitgehaald zou worden.
Dit leek omslachtig en daarom is er gekozen voor de eerste optie.</P>

<H4>Reflectie</H4>

<P>Door uit te gaan van één toestand (is_terrorist) en de andere toestand als false te beschouwen, dacht ik duidelijkheid in de code te creeren. Hoewel dat deels het geval is
vond ik het zelf af en toe zeer verwarrend. Echter heb ik een betere manier om dit te doen nog niet bedacht en dus is dit voorlopig de beste oplossing.</P>

<H3>Costum gridView of Listview?</H3>

<P>Voor het openingscherm wou ik een soort scrollView met plaatjes en tekst. De belangrijkste voorwaarde hieraan was, dat ik er makkelijk nieuwe "maps" aan kon toevoegen.
Wanneer ik dit echter in een scrollView zou doen, leek het erop dat ik die niet zelf-groeiend zou kunnen maken. Uiteindelijk kwam ik uit op een gridView.</P>

<H4>Reflectie</H4>

<P>Zelf ben ik erg tevreden met het resultaat. Het ziet er mooi uit en een map toevoegen kost me één regel code.</P>

<H4>Hulpmiddelen</H4>
<P>https://www.youtube.com/watch?v=g5_vRvcUtic</P>


<H3>Interpoleren</H3>

<P>Een van de problemen waar ik tegenaan liep tijdens het maken van de shadowfunctie was het interpoleren van android. Dit houdt in dat als android een zwarte en een witte pixel naast elkaar heeft, hij hem afrond tot een grijze.
Hierdoor leek mijn plan om pixels met kleuren te herkennen in het water te vallen. De zoektocht om het interpoleren te onzeilen leverde helaas niks op. Zelfs als ik de bmp op precies dezelfde groto had als dat ik hem zou afbeelden,
vondt android het nodig om dit te interpoleren. Aangezien dit probleem zich redelijk laat voordeed, was er ook niet genoeg tijd meer om een ander systeem te maken. De oplossing bleek uiteindelijk de getColor() functie te worden.
Hiermee wordt vrij grof gezocht naar een bijvoorbeeld gele kleur, om vervolgens als precies geel gereturnt te worden.</P>

<H4>Reflectie</H4>

<P>Hoewel het blijkt te werken, is het resultaat niet helemaal optimaal. Het algoritme een stuk langzamer geworden sinds die al die kleuren moet bekijken en ook kleurt hij kaart die de user ziet niet helemaal goed in (lelijke pixels).</P>

<H3>Cirkel "tekenen"</H3>

<P>In de eerste versie van Shadow, werd bij het opstarten één pixel toegevoegd aan de lijst en werden vervolgens de 8 pixels daaromheen gechecked. Dit werkte erg goed, maar was niet realistisch. Wanner je iemand ze maximale beweging vanaf een plek wilt bekijken, zal dit in een cirkel moeten verlopen.
Ik ben vervolgens naar verschillende methodes gaan zoeken om een cirkel te defineren in "vierkante" pixels. Hoewel zeker een aantal methodes/formules van tevinden zijn op het internet. Bleken al deze methodes ongeschikt voor extreem kleine cirkels (zoals ik nodig had). Ik besloot met de kennis die ik had opgedaan, mijn eigen cirkel te defineren.
Hierbij heb ik in paint een pixel grid gemaakt en bedacht welke pixels essentieel zouden zijn voor het vormen van een cirkel.</P>

<P>Nadat het gelukt was om de shadow functie in cirkels te laten verspreiden, ontstond er een nieuw probleem. De thread op de CPU kon het allemaal niet meer aan. Hoewel de CPU maar 20% van zijn totaal verbruikte, benutte hij dit niet.</P>

<P>Ik kon me wel een oplossing voorstellen, waarin de lijst die hij moest verwerken opgesplistst zou worden in twee processen. Echter is het me niet gelukt om dit werkend te krijgen op de shadowfunctie. Uiteindelijk is er dan ook gekozen om niet een cirkel te gebruiken, maar een 8 hoek.</P>

![Figure 1-6](https://github.com/CodingMuziekwijk/csgo-shadow/blob/master/doc/pixels01.jpg)

<H3>Langzaam algoritme</H3>

<P>De cirkel bleek niet het enige probleem te zijn wat het algoritme langzaam maakte. Naarmate er meer "brains" in kwamen, werd het ook aanzienelijk trager. Terugdenkend aan het vak heuristieken, slecht filteren hierbij het probleem.
Elke pixel in de TODO lijst moest een stuk of 40 pixels checken, waarvan er eigenlijk al een stuk of 25 uitgesloten konden worden.</P>

<H4>Reflectie</H4>

<P>Sinds er beter gefilterd wordt op het bekijken van pixels, presteert het algoritme een stuk sneller. Echter ben ik nog niet 100% tevreden, omdat hij soms nog een kleine vertraging veroorzaakt als de TODO-lijst groot wordt.</P>

<H3>Shadow weergeven op het scherm</H3>

<P>Om Shadow op de kaart te weergeven, is er in een eerder versie gekozen om de betreffende pixels felgroen te kleuren. Later is dit aangepast en wordt de groen tint alleen verhoogd. Hierdoor blijft het detail van de oorspronkelijke kaart behouden.</P>

<H4>Reflectie</H4>

<P>Persoonlijk ben ik erg tevreden over het uiteindelijke visualisatie. Het ziet er mooi uit en dient het doel van overzichtelijk informatie tonen aan de gebruiker.</P>

<H3>Hoe accuraat moet de EcoFunctie zijn?</H3>

<P>Hoewel in mijn viable product de EcoFunctie niet een noodzakelijke functie was, vondt ik het gedurende het project toch belangrijk om hem er wel in te hebben. Gezien de tijd moesten er consessies gedaan worden betreft nauwkeurigheid.
Ik heb er voor gekozen om de gebruiker alleen win/lose en eco/!eco als input te laten geven. Hierdoor wordt een vrij onnauwkeurige voorspelling gedaan, maar wordt het principe van de functie wel duidelijk.</P>

<H3>Overige hulpmiddelen</H3>

<P>Voor het herhalen van een stuk code met een bepaald interval: http://www.jguru.com/faq/view.jsp?EID=99038</P>
<P>De timer vroegtijdig stopzetten: http://stackoverflow.com/questions/14252592/timer-scheduleatfixedrate-does-not-stop-when-i-call-cancel</P>
<P>Het maken van een cirkel: http://rosettacode.org/wiki/Bitmap/Midpoint_circle_algorithm#Java</P>
<P>Een countdown timer maken: https://www.youtube.com/watch?v=K-HbZ4C7skw</P>
<P>Verschillende soort cirkels in pixels afgebeeld: http://www.minecraftguides.org/images/guide/circles3.png</P>
<P>Pixels extracten uit een bmp en bewerken: http://stackoverflow.com/questions/12340308/how-to-alter-color-value-of-a-pixel-in-a-bitmap-image-and-then-extract-it</P>
