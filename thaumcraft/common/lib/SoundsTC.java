/*     */ package thaumcraft.common.lib;public class SoundsTC { public static SoundEvent zap; public static SoundEvent heartbeat; public static SoundEvent spill; public static SoundEvent bubble; public static SoundEvent creak; public static SoundEvent squeek; public static SoundEvent jar; public static SoundEvent swarm; public static SoundEvent swarmattack; public static SoundEvent fly; public static SoundEvent key; public static SoundEvent ticks; public static SoundEvent clack; public static SoundEvent pump; public static SoundEvent poof; public static SoundEvent page;
/*     */   public static SoundEvent learn;
/*     */   public static SoundEvent write;
/*     */   public static SoundEvent erase;
/*     */   public static SoundEvent brain;
/*     */   public static SoundEvent crystal;
/*     */   public static SoundEvent wispdead;
/*     */   public static SoundEvent wisplive;
/*     */   public static SoundEvent wand;
/*     */   public static SoundEvent wandfail;
/*     */   public static SoundEvent rumble;
/*     */   public static SoundEvent ice;
/*     */   public static SoundEvent jacobs;
/*     */   public static SoundEvent hhoff;
/*     */   public static SoundEvent hhon;
/*     */   public static SoundEvent pech_idle;
/*     */   public static SoundEvent pech_trade;
/*     */   public static SoundEvent pech_dice;
/*     */   public static SoundEvent pech_hit;
/*     */   
/*     */   public static void registerSoundTypes() {
/*  22 */     GORE = new SoundType(0.5F, 1.0F, gore, gore, gore, gore, gore);
/*  23 */     CRYSTAL = new SoundType(0.5F, 1.0F, crystal, crystal, crystal, crystal, crystal);
/*  24 */     JAR = new SoundType(0.5F, 1.0F, jar, jar, jar, jar, jar);
/*  25 */     URN = new SoundType(0.5F, 1.5F, urnbreak, urnbreak, urnbreak, urnbreak, urnbreak);
/*     */   }
/*     */   public static SoundEvent pech_death; public static SoundEvent pech_charge; public static SoundEvent shock; public static SoundEvent craftfail; public static SoundEvent scan; public static SoundEvent craftstart; public static SoundEvent runicShieldEffect; public static SoundEvent runicShieldCharge; public static SoundEvent wind; public static SoundEvent tool; public static SoundEvent gore; public static SoundEvent tentacle; public static SoundEvent upgrade; public static SoundEvent whispers; public static SoundEvent monolith; public static SoundEvent infuser; public static SoundEvent infuserstart; public static SoundEvent egidle; public static SoundEvent egattack; public static SoundEvent egdeath; public static SoundEvent egscreech; public static SoundEvent crabclaw; public static SoundEvent crabdeath; public static SoundEvent crabtalk; public static SoundEvent chant; public static SoundEvent coins; public static SoundEvent urnbreak; public static SoundEvent evilportal; public static SoundEvent grind; public static SoundEvent dust; public static SoundEvent pageturn; public static SoundType GORE; public static SoundType CRYSTAL; public static SoundType JAR; public static SoundType URN;
/*     */   
/*     */   public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
/*  30 */     zap = getRegisteredSoundEvent(event, "thaumcraft:zap");
/*  31 */     heartbeat = getRegisteredSoundEvent(event, "thaumcraft:heartbeat");
/*  32 */     spill = getRegisteredSoundEvent(event, "thaumcraft:spill");
/*  33 */     bubble = getRegisteredSoundEvent(event, "thaumcraft:bubble");
/*  34 */     creak = getRegisteredSoundEvent(event, "thaumcraft:creak");
/*  35 */     squeek = getRegisteredSoundEvent(event, "thaumcraft:squeek");
/*  36 */     jar = getRegisteredSoundEvent(event, "thaumcraft:jar");
/*  37 */     swarm = getRegisteredSoundEvent(event, "thaumcraft:swarm");
/*  38 */     swarmattack = getRegisteredSoundEvent(event, "thaumcraft:swarmattack");
/*  39 */     fly = getRegisteredSoundEvent(event, "thaumcraft:fly");
/*  40 */     key = getRegisteredSoundEvent(event, "thaumcraft:key");
/*  41 */     ticks = getRegisteredSoundEvent(event, "thaumcraft:ticks");
/*  42 */     clack = getRegisteredSoundEvent(event, "thaumcraft:clack");
/*  43 */     pump = getRegisteredSoundEvent(event, "thaumcraft:pump");
/*  44 */     poof = getRegisteredSoundEvent(event, "thaumcraft:poof");
/*  45 */     page = getRegisteredSoundEvent(event, "thaumcraft:page");
/*  46 */     pageturn = getRegisteredSoundEvent(event, "thaumcraft:pageturn");
/*  47 */     learn = getRegisteredSoundEvent(event, "thaumcraft:learn");
/*  48 */     write = getRegisteredSoundEvent(event, "thaumcraft:write");
/*  49 */     erase = getRegisteredSoundEvent(event, "thaumcraft:erase");
/*  50 */     brain = getRegisteredSoundEvent(event, "thaumcraft:brain");
/*  51 */     crystal = getRegisteredSoundEvent(event, "thaumcraft:crystal");
/*  52 */     wispdead = getRegisteredSoundEvent(event, "thaumcraft:wispdead");
/*  53 */     wisplive = getRegisteredSoundEvent(event, "thaumcraft:wisplive");
/*  54 */     wand = getRegisteredSoundEvent(event, "thaumcraft:wand");
/*  55 */     wandfail = getRegisteredSoundEvent(event, "thaumcraft:wandfail");
/*  56 */     rumble = getRegisteredSoundEvent(event, "thaumcraft:rumble");
/*  57 */     ice = getRegisteredSoundEvent(event, "thaumcraft:ice");
/*  58 */     jacobs = getRegisteredSoundEvent(event, "thaumcraft:jacobs");
/*  59 */     hhoff = getRegisteredSoundEvent(event, "thaumcraft:hhoff");
/*  60 */     hhon = getRegisteredSoundEvent(event, "thaumcraft:hhon");
/*  61 */     pech_idle = getRegisteredSoundEvent(event, "thaumcraft:pech_idle");
/*  62 */     pech_trade = getRegisteredSoundEvent(event, "thaumcraft:pech_trade");
/*  63 */     pech_dice = getRegisteredSoundEvent(event, "thaumcraft:pech_dice");
/*  64 */     pech_hit = getRegisteredSoundEvent(event, "thaumcraft:pech_hit");
/*  65 */     pech_death = getRegisteredSoundEvent(event, "thaumcraft:pech_death");
/*  66 */     pech_charge = getRegisteredSoundEvent(event, "thaumcraft:pech_charge");
/*  67 */     shock = getRegisteredSoundEvent(event, "thaumcraft:shock");
/*  68 */     craftfail = getRegisteredSoundEvent(event, "thaumcraft:craftfail");
/*  69 */     scan = getRegisteredSoundEvent(event, "thaumcraft:scan");
/*  70 */     craftstart = getRegisteredSoundEvent(event, "thaumcraft:craftstart");
/*  71 */     runicShieldEffect = getRegisteredSoundEvent(event, "thaumcraft:runicshieldeffect");
/*  72 */     runicShieldCharge = getRegisteredSoundEvent(event, "thaumcraft:runicshieldcharge");
/*  73 */     wind = getRegisteredSoundEvent(event, "thaumcraft:wind");
/*  74 */     tool = getRegisteredSoundEvent(event, "thaumcraft:tool");
/*  75 */     gore = getRegisteredSoundEvent(event, "thaumcraft:gore");
/*  76 */     tentacle = getRegisteredSoundEvent(event, "thaumcraft:tentacle");
/*  77 */     upgrade = getRegisteredSoundEvent(event, "thaumcraft:upgrade");
/*  78 */     whispers = getRegisteredSoundEvent(event, "thaumcraft:whispers");
/*  79 */     monolith = getRegisteredSoundEvent(event, "thaumcraft:monolith");
/*  80 */     infuser = getRegisteredSoundEvent(event, "thaumcraft:infuser");
/*  81 */     infuserstart = getRegisteredSoundEvent(event, "thaumcraft:infuserstart");
/*  82 */     egidle = getRegisteredSoundEvent(event, "thaumcraft:egidle");
/*  83 */     egattack = getRegisteredSoundEvent(event, "thaumcraft:egattack");
/*  84 */     egdeath = getRegisteredSoundEvent(event, "thaumcraft:egdeath");
/*  85 */     egscreech = getRegisteredSoundEvent(event, "thaumcraft:egscreech");
/*  86 */     crabclaw = getRegisteredSoundEvent(event, "thaumcraft:crabclaw");
/*  87 */     crabdeath = getRegisteredSoundEvent(event, "thaumcraft:crabdeath");
/*  88 */     crabtalk = getRegisteredSoundEvent(event, "thaumcraft:crabtalk");
/*  89 */     chant = getRegisteredSoundEvent(event, "thaumcraft:chant");
/*  90 */     coins = getRegisteredSoundEvent(event, "thaumcraft:coins");
/*  91 */     urnbreak = getRegisteredSoundEvent(event, "thaumcraft:urnbreak");
/*  92 */     evilportal = getRegisteredSoundEvent(event, "thaumcraft:evilportal");
/*  93 */     grind = getRegisteredSoundEvent(event, "thaumcraft:grind");
/*  94 */     dust = getRegisteredSoundEvent(event, "thaumcraft:dust");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static SoundEvent getRegisteredSoundEvent(RegistryEvent.Register<SoundEvent> event, String id) {
/* 102 */     SoundEvent soundevent = new SoundEvent(new ResourceLocation(id));
/* 103 */     soundevent.setRegistryName(new ResourceLocation(id));
/* 104 */     event.getRegistry().register(soundevent);
/* 105 */     return soundevent;
/*     */   } }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\SoundsTC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */