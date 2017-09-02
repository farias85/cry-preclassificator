/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 * Created by Felipe Rodriguez Arias <ucifarias@gmail.com>.
 */
package preclass.algorithm;

public interface AudioData {

    /**
     * Primer arreglo para probar LPC
     */
    public static double[] DATA = {0.00000009521931597639925, -0.00000026758223901598011,
        0.00000035099397322765859, 0.00000169744630238650210, -0.00000121107082160237120,
        0.00000396667121559976960, -0.00000506342724864010470, 0.00000697774936208379980,
        -0.00000919718647570730550, 0.00000321742089170199840, -0.00000074469232278273196,
        -0.00001404020776717932000, 0.00003545873682482959600, -0.00005556555258900972800,
        0.00008417175722485853100, -0.00008954019697595286900, 0.00008727704242868252700,
        -0.00004895093480241573600, -0.00003009975847484595300, 0.00016057707014164021000,
        -0.00031400911597365529000, 0.00053250737636403053000, -0.00073426487636476829000,
        0.00095101366347914772000, -0.00112420898847380330000, -0.00098678592409301414000,
        0.00509213740323938460000, -0.01012429523379480000000, -0.02336488111327423400000,
        -0.02282554013624928100000, -0.01637977183779655900000, -0.00918053207868815140000,
        -0.00128316176687102380000, 0.02160199662541900300000, 0.03432770306674592200000,
        0.03560075310978051700000, 0.01026680464060188700000, -0.01775690944293240400000,
        -0.01868166716077193300000, -0.03743716802730091100000, -0.03776381566850899700000,
        -0.01535707023106520700000, -0.00266450159664783580000, 0.00092750696849656347000,
        -0.02537989490957318400000, -0.04939480077268452300000, -0.04793704293684605800000,
        -0.03815457866996736100000, -0.02061656387521332600000, 0.00224379884660669010000,
        0.02912635875456093600000, 0.04437907871545809800000, 0.03609914204707750200000,
        0.01090556399827640600000, -0.01613632689295251300000, -0.02242979997036955200000,
        -0.02897959896984653500000, -0.02929590792451055400000, -0.00799801316385490880000,
        0.00760404469685035420000, 0.01698046579629763900000, 0.00848539823238273620000,
        -0.00758281246235793530000, -0.01383500366891326500000, -0.01686132629003591100000,
        -0.00962924985908020750000, -0.00249713045321396130000, 0.00507610235609913360000,
        0.01234676477700158900000, 0.01133377009420256100000, 0.00962937147231320880000,
        0.00377293553743635570000, 0.00018769704821163471000, 0.00082208133921464195000,
        -0.00190056274704176040000, -0.00082913406196539368000, 0.00214410374775920310000,
        0.00296899337062748670000, 0.00340449264553681830000, 0.00149251585512756300000};
    
    /**
     * Frecuencia de Muestreo
     */
    public static int SAMPLE_RATE = 8000;
    
    /**
     * Paso 1. filtrado de la trama de señal , filtro FIR paso bajo con
     * frecuencia de corte 3500 Hz vector de coeficientes del filtro
     */
    public static double[] B = {-0.0001233090141894370000000000000, -0.0001408293683290947900000000000,
        0.0000951135021086792290000000000, -0.0001483115160485374100000000000, 0.0003555927888984708600000000000,
        -0.0002971613262942691600000000000, 0.0003813616571681618900000000000, -0.0003201952289107046300000000000,
        0.0000716985653351569600000000000, 0.0002964546066511422200000000000, -0.0006270953185598328500000000000,
        0.0013404759248063932000000000000, -0.0016416631880123349000000000000, 0.0020664359394207059000000000000,
        -0.0019036274109720259000000000000, 0.0014870391757945954000000000000, -0.0006602626745904191200000000000,
        -0.0011403584407646496000000000000, 0.0031503340150567057000000000000, -0.0056219433529019428000000000000,
        0.0083964700756930886000000000000, -0.0108806401544818980000000000000, 0.0130917013227877830000000000000,
        -0.0146457536067479320000000000000, 0.0151707057885214420000000000000, 0.3016901552047767100000000000000,
        0.3744692638234717300000000000000, 0.1515647847734194500000000000000, -0.0811309498245546680000000000000,
        -0.2167466011039737300000000000000, -0.2199595606911192700000000000000, -0.0966640216682147640000000000000,
        0.0497298801963981590000000000000, 0.0603870669678751580000000000000, -0.0669699312468805540000000000000,
        -0.2581671198390663200000000000000, -0.3479445150571166000000000000000, -0.2848215377003749400000000000000,
        -0.2286501414535501400000000000000, -0.2546780547178866300000000000000, -0.0217250049373918230000000000000,
        0.3570350337640722500000000000000, 0.3592599241352892300000000000000, 0.1326132727795179300000000000000,
        -0.0675526609824641660000000000000, -0.1811492538840344400000000000000, -0.1626470866350293900000000000000,
        -0.0118857625631914530000000000000, 0.0982486967071824340000000000000, 0.0754950730299969170000000000000,
        -0.0652140890230293590000000000000};
    
    /**
     * % A partir de aqui el procesamiento es por cada segmentos Paso 2.
     * multiplicar segmento de señal (s) por ventana de hamming (win)
     */
    public static double[] WINDOW = {0.0800000000000000160000000000000, 0.0814541340532399240000000000000,
        0.0858073427092866940000000000000, 0.0930321035814113960000000000000, 0.1030827394052200000000000000000,
        0.1158957068250249200000000000000, 0.1313899981353993700000000000000, 0.1494676534379725800000000000000,
        0.1700143799754481600000000000000, 0.1929002747272141600000000000000, 0.2179806456980643600000000000000,
        0.2450969267075787700000000000000, 0.2740776798965697800000000000000, 0.3047396796124257600000000000000,
        0.3368890708206805400000000000000, 0.3703225947189564500000000000000, 0.4048288738045563400000000000000,
        0.4401897482710937500000000000000, 0.4761816552850285900000000000000, 0.5125770424218828400000000000000,
        0.5491458063259323500000000000000, 0.5856567474977082400000000000000, 0.6218790320116730500000000000000,
        0.6575836509226183900000000000000, 0.6925448681339512200000000000000, 0.7265416475739772100000000000000,
        0.7593590506571127300000000000000, 0.7907895951948269400000000000000, 0.8206345671648411800000000000000,
        0.8487052770451570800000000000000, 0.8748242527699673400000000000000, 0.8988263617651975900000000000000,
        0.9205598549698063000000000000000, 0.9398873262422080500000000000000, 0.9566865810861406900000000000000,
        0.9708514092036149000000000000000, 0.9822922559906134300000000000000, 0.9909367887301325800000000000000,
        0.9967303539029077600000000000000, 0.9996363227245588200000000000000, 0.9996363227245588200000000000000,
        0.9967303539029077600000000000000, 0.9909367887301325800000000000000, 0.9822922559906134300000000000000,
        0.9708514092036149000000000000000, 0.9566865810861406900000000000000, 0.9398873262422080500000000000000,
        0.9205598549698063000000000000000, 0.8988263617651975900000000000000};
    
    /**
     * 80 valores de señal (s)
     */
    public static double[] S = {-0.2466060219204516400000000000000, -0.0677920633963355610000000000000,
        -0.0103968648270663050000000000000, -0.0391924311541550500000000000000, -0.1105620775535947300000000000000,
        -0.0598173365548206810000000000000, 0.1709728984168008300000000000000, 0.3178510130411408800000000000000,
        0.0155050376565056250000000000000, -0.4281793181229625600000000000000, -0.4638257515796482800000000000000,
        -0.2050544094636046200000000000000, 0.2397063645884687000000000000000, 0.5302013413413657600000000000000,
        0.3563973694873467400000000000000, 0.0190562497280216540000000000000, -0.2733503114629777800000000000000,
        -0.2985501873326427100000000000000, -0.1179256231088596900000000000000, -0.0307243936309200930000000000000,
        -0.0406862844336350060000000000000, -0.1265068705624208800000000000000, -0.1177890662057955500000000000000,
        0.0811125367868698460000000000000, 0.2711059967271704500000000000000, 0.0746015765009611230000000000000,
        -0.3814432240183503500000000000000, -0.4693895919497132500000000000000, -0.2534029169712680400000000000000,
        0.1399100621424041500000000000000, 0.5102432367221555500000000000000, 0.4042403616395742700000000000000,
        0.0728148917286893650000000000000, -0.2492745974220698400000000000000, -0.3425263470068695100000000000000,
        -0.1340886341518912300000000000000, -0.0020431577429603663000000000000, 0.0180853156125609230000000000000,
        -0.0493852468152215720000000000000, -0.0785681402720686540000000000000, 0.0619945621778280680000000000000,
        0.2522139055985505600000000000000, 0.1778469215150209100000000000000, -0.2682806485585383400000000000000,
        -0.4766217592326233400000000000000, -0.3175923853999773600000000000000, 0.0322761000541961840000000000000,
        0.4683108512078884000000000000000, 0.4720979627952531800000000000000, 0.1446061711202690800000000000000,
        -0.1977255021190826300000000000000, -0.3722874860006951700000000000000, -0.2112290746200493100000000000000,
        -0.0387818917344125780000000000000, 0.0256532519459499700000000000000, -0.0092418179511192869000000000000,
        -0.0911233058393955550000000000000, -0.0052231159465719931000000000000, 0.2181936401885973500000000000000,
        0.2585240462694185500000000000000, -0.1287016878334472400000000000000, -0.4501942551793030600000000000000,
        -0.3856513752613583400000000000000, -0.0594048402071771370000000000000, 0.4043578698311342500000000000000,
        0.4941270326808545600000000000000, 0.2012089751419682000000000000000, -0.1466265232187929600000000000000,
        -0.3586900313359174800000000000000, -0.2215780110297280200000000000000, -0.0123742665188795420000000000000,
        0.0481238831982470290000000000000, -0.0300690031743042830000000000000, -0.1396747320073400100000000000000,
        -0.0654614617014408070000000000000, 0.1793184321369516700000000000000, 0.3056662700355295400000000000000,
        -0.0327110414698833780000000000000, -0.4234641658026980200000000000000, -0.4163776416886302300000000000000};

    /**
     * 80 valores de señal (swin) resultado de la ventana de hamming (win) a la
     * señal (s)
     */
    public static double[] S_WINDOW = {-0.0197284817536361350000000000000, -0.0055219438196308559000000000000,
        -0.0008921273433182071300000000000,
        -0.0036461543147406875000000000000, -0.0113970418285569300000000000000, -0.0069325725004113440000000000000,
        0.0224641288041872860000000000000, 0.0475084450621417460000000000000, 0.0026360793636667798000000000000,
        -0.0825959080984307040000000000000, -0.1011050368207217200000000000000, -0.0502582055673669450000000000000,
        0.0656981642628487740000000000000, 0.1615733868904462000000000000000, 0.1200663786495270000000000000000,
        0.0070569598448933868000000000000, -0.1106600987436820000000000000000, -0.1314187318082438700000000000000,
        -0.0561540184124952250000000000000, -0.0157486188175427550000000000000, -0.0223427024717147250000000000000,
        -0.0740896023497009810000000000000, -0.0732505504736190030000000000000, 0.0533382780759050630000000000000,
        0.1877530667537416800000000000000, 0.0542011523026243930000000000000, -0.2896523644701629000000000000000,
        -0.3711884054065787100000000000000, -0.2079511930870247300000000000000, 0.1187424080519742500000000000000,
        0.4463731582963892900000000000000, 0.3633418935311462600000000000000, 0.0670304661694044320000000000000,
        -0.2342900348711320400000000000000, -0.3276903598499270600000000000000, -0.1301801394245515600000000000000,
        -0.0020069780286772281000000000000, 0.0179214045762820520000000000000, -0.0492237745357182450000000000000,
        -0.0785395668248780320000000000000, 0.0619720161643630700000000000000, 0.2513892553864778800000000000000,
        0.1762350572916347400000000000000, -0.2635300035111915500000000000000, -0.4627289066080984000000000000000,
        -0.3038363733672963000000000000000, 0.0303358973814644370000000000000, 0.4311081692687203100000000000000,
        0.4243340942960190400000000000000, 0.1265049855962154200000000000000, -0.1678106770548688200000000000000,
        -0.3055119799350673500000000000000, -0.1670377544121666700000000000000, -0.0294493804901304610000000000000,
        0.0186381559344408270000000000000, -0.0064003735942758898000000000000, -0.0599211961380080800000000000000,
        -0.0032481462889389244000000000000, 0.1277865776375391700000000000000, 0.1419673958432624800000000000000,
        -0.0659695305043728060000000000000, -0.2143742456310910900000000000000, -0.1697597817966984300000000000000,
        -0.0240487945596111470000000000000, 0.1497428555508956700000000000000, 0.1664659969072331300000000000000,
        0.0613163586199079320000000000000, -0.0401870572951072980000000000000, -0.0879138243210784990000000000000,
        -0.0482997179167529360000000000000, -0.0023869994110396315000000000000, 0.0081817521639608558000000000000,
        -0.0044943433456822099000000000000, -0.0183518627780068120000000000000, -0.0075867023736877811000000000000,
        0.0184846352105260160000000000000, 0.0284367760952890510000000000000, -0.0028068475457839722000000000000,
        -0.0344929069280363810000000000000, -0.0333102113350904270000000000000};

    /**
     * valores de respuesta de LPC (goooooood) Paso 3. Calcular los coeficientes
     * LPC(a) del segmento enventanado de señal (swin) a = lpc(swin,12);
     */
    public static double[] A = {1.0000000000000000000000000000000, -1.4516972409301880000000000000000,
        1.6470693454936978000000000000000, -1.2541743799959209000000000000000, 1.5491965545694362000000000000000,
        -1.6702642541847663000000000000000, 1.6102741354750261000000000000000, -1.3900934574606807000000000000000,
        1.2867642066257496000000000000000, -1.1942670031558102000000000000000, 0.8546034545233995900000000000000,
        -0.6006920565756830300000000000000, 0.3081342713356927100000000000000};

    /**
     * Despues de filtrar la señal con los coeficientes del polinomio de LPC se
     * obtiene este vector para realizar la correlacion Paso 4. Filtrar la señal
     * (swin) con los coeficientes LPs (a) error = filter(a,1,swin);
     */
    public static double[] ERROR = {-0.0197284817536361350000000000000, 0.0231178387098642800000000000000,
        -0.0253701142653344200000000000000, 0.0132968764668878050000000000000, -0.0312111407625595760000000000000,
        0.0291230974563419720000000000000, -0.0055979188535143706000000000000, 0.0321468502864452750000000000000,
        -0.0513498390077892220000000000000, -0.0162263068821859450000000000000, -0.0147594523733391160000000000000,
        0.0014322724849454710000000000000, 0.0324277320305728230000000000000, 0.0235471790937805130000000000000,
        0.0056425238919424470000000000000, 0.0049303920439041438000000000000, -0.0215504704564224160000000000000,
        0.0059980086842255920000000000000, -0.0155174476427798120000000000000, -0.0341130849109486030000000000000,
        -0.0330495659457731850000000000000, -0.0496913579218813820000000000000, -0.0215188655207637840000000000000,
        0.0260887332212674380000000000000, 0.0415224531267024130000000000000, -0.1166529850437313900000000000000,
        -0.1069262409593801900000000000000, 0.0229645936863052120000000000000, -0.0404249792671713400000000000000,
        0.0417366124848772830000000000000, 0.0532396313053024590000000000000, 0.0081295129605000183000000000000,
        0.0348270670322596700000000000000, -0.0444895546613387480000000000000, 0.0098528665830841522000000000000,
        -0.0085621002494352394000000000000, -0.0547555646300006210000000000000, -0.0183232517253188110000000000000,
        -0.0401803721438461210000000000000, -0.0533785349594799590000000000000, -0.0168313814545554830000000000000,
        0.0682509377206079180000000000000, -0.0818761401283007450000000000000, -0.0955393944579226440000000000000,
        0.0520673067195566160000000000000, -0.0204253419371543950000000000000, -0.0073461254104219345000000000000,
        0.0171453592679016160000000000000, 0.0072537020111750339000000000000, 0.0327953431093420120000000000000,
        -0.0060213804752483890000000000000, -0.0107106447501573170000000000000, -0.0120581170950244120000000000000,
        -0.0323919885806553120000000000000, 0.0012289873221986714000000000000, 0.0039664325984205238000000000000,
        -0.0805460306180575680000000000000, -0.0628937926645730530000000000000, 0.0216189222586965350000000000000,
        -0.0366623345617837220000000000000, -0.0074311486571688873000000000000, 0.0540788524934909810000000000000,
        0.0187505028060188370000000000000, -0.0128599443422109110000000000000, -0.0424377207771067800000000000000,
        -0.0069247385952913576000000000000, 0.0146389679703375770000000000000, 0.0112808423879743880000000000000,
        -0.0023100644022457928000000000000, -0.0122318108619196910000000000000, 0.0001352122849951780700000000000,
        0.0095411866662227802000000000000, 0.0233055753860175250000000000000, -0.0225572438517582660000000000000,
        -0.0371327007483012240000000000000, -0.0140337190496684530000000000000, -0.0117906279494298240000000000000,
        0.0144010638358378660000000000000, 0.0160383279950791420000000000000, 0.0070071771763776589000000000000};

    /**
     * % Paso 5. calcular la función de autocorrelación del error ecorr =
     * xcorr(error,'coeff');
     */
    public static double[] ECORR = {-0.0012687133947544395000000000000, -0.0014172070940947853000000000000,
        -0.0008361898137257366300000000000, 0.0023110173700360177000000000000, -0.0033636531066630368000000000000,
        0.0055272482780083952000000000000, -0.0021637224287909106000000000000, 0.0063974667648283579000000000000,
        0.0054959705371659916000000000000, -0.0030411434501578559000000000000, -0.0134794338174630090000000000000,
        -0.0146499005756650630000000000000, 0.0027465662131126939000000000000, 0.0211101682892347700000000000000,
        0.0373974981508900590000000000000, 0.0023552853831146784000000000000, -0.0184956780023426100000000000000,
        -0.0235225222108930400000000000000, -0.0175657566176499850000000000000, 0.0209326208533207050000000000000,
        -0.0135831306405953360000000000000, -0.0224904374345857580000000000000, -0.0062310810043390190000000000000,
        0.0192110001928741980000000000000, 0.0540188249328645840000000000000, 0.0431913448941283890000000000000,
        -0.0077164439475396881000000000000, -0.0604787574223565670000000000000, -0.0320643784977621620000000000000,
        -0.0190560703876139530000000000000, 0.0836004333308962570000000000000, 0.1511156742836807000000000000000,
        0.0028452186014084431000000000000, -0.0333771441624162860000000000000, -0.0438349849365385970000000000000,
        -0.0433218405002853340000000000000, 0.0471581355549872410000000000000, -0.0305664398115224130000000000000,
        -0.0572480744067598370000000000000, -0.0315368814135075990000000000000, 0.0331416654891873930000000000000,
        0.1254881387899620400000000000000, 0.0895385092980476860000000000000, -0.0165626915850047430000000000000,
        0.0048375887065471978000000000000, 0.1193340510525384000000000000000, -0.0736947999763168100000000000000,
        0.0625477905837456410000000000000, 0.2661714973550888300000000000000, -0.0046677090806021828000000000000,
        -0.0181862708534590960000000000000, 0.0150585111415998010000000000000, -0.0215233956294416070000000000000,
        0.0030558266384497533000000000000, -0.0274529650152832980000000000000, -0.0276962401934053170000000000000,
        -0.0187152020727522660000000000000, 0.0049132667532460249000000000000, 0.0542074197987456530000000000000,
        0.1214959901573263700000000000000, -0.0960680009943313400000000000000, 0.1836296824212787800000000000000,
        0.5963916376734649200000000000000, -0.0435956377954621330000000000000, -0.0793706841412341600000000000000,
        0.2015988188118604800000000000000, -0.0675014597442431250000000000000, 0.0063714801535322716000000000000,
        0.0784969541796120000000000000000, 0.0716935124682576000000000000000, -0.0686422700980477830000000000000,
        -0.0369090237728675890000000000000, 0.0539100888017662800000000000000, 0.0643101102834540830000000000000,
        -0.0183589786955982370000000000000, -0.0485696411197120360000000000000, 0.0976981362202016260000000000000,
        -0.1220246234438756000000000000000, 0.1090753618677701800000000000000, 1.0000000000000000000000000000000,
        0.1090753618677701800000000000000, -0.1220246234438756000000000000000, 0.0976981362202016120000000000000,
        -0.0485696411197120290000000000000, -0.0183589786955982370000000000000, 0.0643101102834540830000000000000,
        0.0539100888017662800000000000000, -0.0369090237728675890000000000000, -0.0686422700980477830000000000000,
        0.0716935124682576000000000000000, 0.0784969541796120000000000000000, 0.0063714801535322595000000000000,
        -0.0675014597442431250000000000000, 0.2015988188118604500000000000000, -0.0793706841412341190000000000000,
        -0.0435956377954621610000000000000, 0.5963916376734649200000000000000, 0.1836296824212788300000000000000,
        -0.0960680009943313120000000000000, 0.1214959901573263400000000000000, 0.0542074197987456530000000000000,
        0.0049132667532460310000000000000, -0.0187152020727522660000000000000, -0.0276962401934053130000000000000,
        -0.0274529650152833120000000000000, 0.0030558266384497416000000000000, -0.0215233956294416070000000000000,
        0.0150585111415998010000000000000, -0.0181862708534590960000000000000, -0.0046677090806021993000000000000,
        0.2661714973550888800000000000000, 0.0625477905837456410000000000000, -0.0736947999763167830000000000000,
        0.1193340510525384000000000000000, 0.0048375887065471952000000000000, -0.0165626915850047470000000000000,
        0.0895385092980476860000000000000, 0.1254881387899620400000000000000, 0.0331416654891873930000000000000,
        -0.0315368814135075990000000000000, -0.0572480744067598440000000000000, -0.0305664398115224060000000000000,
        0.0471581355549872470000000000000, -0.0433218405002853410000000000000, -0.0438349849365386040000000000000,
        -0.0333771441624162860000000000000, 0.0028452186014084513000000000000, 0.1511156742836806400000000000000,
        0.0836004333308962700000000000000, -0.0190560703876139420000000000000, -0.0320643784977621620000000000000,
        -0.0604787574223565540000000000000, -0.0077164439475396855000000000000, 0.0431913448941283890000000000000,
        0.0540188249328645700000000000000, 0.0192110001928741980000000000000, -0.0062310810043390355000000000000,
        -0.0224904374345857510000000000000, -0.0135831306405953360000000000000, 0.0209326208533207050000000000000,
        -0.0175657566176499820000000000000, -0.0235225222108930400000000000000, -0.0184956780023425860000000000000,
        0.0023552853831146784000000000000, 0.0373974981508900660000000000000, 0.0211101682892347530000000000000,
        0.0027465662131127017000000000000, -0.0146499005756650610000000000000, -0.0134794338174630040000000000000,
        -0.0030411434501578559000000000000, 0.0054959705371660020000000000000, 0.0063974667648283518000000000000,
        -0.0021637224287909102000000000000, 0.0055272482780083960000000000000, -0.0033636531066630429000000000000,
        0.0023110173700360238000000000000, -0.0008361898137257445400000000000, -0.0014172070940947693000000000000,
        -0.0012687133947544389000000000000};
}
