/*     */ package thaumcraft.common.lib.research;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.JsonToNBT;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.TextComponentTranslation;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import thaumcraft.Thaumcraft;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.capabilities.IPlayerKnowledge;
/*     */ import thaumcraft.api.capabilities.IPlayerWarp;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.api.internal.CommonInternals;
/*     */ import thaumcraft.api.research.ResearchAddendum;
/*     */ import thaumcraft.api.research.ResearchCategories;
/*     */ import thaumcraft.api.research.ResearchCategory;
/*     */ import thaumcraft.api.research.ResearchEntry;
/*     */ import thaumcraft.api.research.ResearchEntry.EnumResearchMeta;
/*     */ import thaumcraft.api.research.ResearchEvent;
/*     */ import thaumcraft.api.research.ResearchStage;
/*     */ import thaumcraft.api.research.ResearchStage.Knowledge;
/*     */ import thaumcraft.common.config.ModConfig;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.misc.PacketKnowledgeGain;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ResearchManager
/*     */ {
/*  49 */   public static ConcurrentHashMap<String, Boolean> syncList = new ConcurrentHashMap();
/*     */   
/*     */   public static boolean addKnowledge(EntityPlayer player, IPlayerKnowledge.EnumKnowledgeType type, ResearchCategory category, int amount) {
/*  52 */     IPlayerKnowledge knowledge = ThaumcraftCapabilities.getKnowledge(player);
/*  53 */     if (!type.hasFields()) {
/*  54 */       category = null;
/*     */     }
/*     */     
/*  57 */     if (MinecraftForge.EVENT_BUS.post(new ResearchEvent.Knowledge(player, type, category, amount))) {
/*  58 */       return false;
/*     */     }
/*  60 */     int kp = knowledge.getKnowledge(type, category);
/*  61 */     knowledge.addKnowledge(type, category, amount);
/*  62 */     int kr = knowledge.getKnowledge(type, category) - kp;
/*  63 */     if (amount > 0) {
/*  64 */       for (int a = 0; a < kr; a++) {
/*  65 */         PacketHandler.INSTANCE.sendTo(new PacketKnowledgeGain((byte)type.ordinal(), (category == null) ? null : category.key), (EntityPlayerMP)player);
/*     */       }
/*     */     }
/*  68 */     syncList.put(player.func_70005_c_(), Boolean.valueOf(true));
/*  69 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean completeResearch(EntityPlayer player, String researchkey, boolean sync) {
/*  73 */     boolean b = false;
/*  74 */     for (; progressResearch(player, researchkey, sync); b = true);
/*  75 */     return b;
/*     */   }
/*     */   
/*     */   public static boolean completeResearch(EntityPlayer player, String researchkey) {
/*  79 */     boolean b = false;
/*  80 */     for (; progressResearch(player, researchkey, true); b = true);
/*  81 */     return b;
/*     */   }
/*     */   
/*     */   public static boolean startResearchWithPopup(EntityPlayer player, String researchkey) {
/*  85 */     boolean b = progressResearch(player, researchkey, true);
/*  86 */     if (b) {
/*  87 */       IPlayerKnowledge knowledge = ThaumcraftCapabilities.getKnowledge(player);
/*  88 */       knowledge.setResearchFlag(researchkey, IPlayerKnowledge.EnumResearchFlag.POPUP);
/*  89 */       knowledge.setResearchFlag(researchkey, IPlayerKnowledge.EnumResearchFlag.RESEARCH);
/*     */     } 
/*  91 */     return b;
/*     */   }
/*     */ 
/*     */   
/*  95 */   public static boolean progressResearch(EntityPlayer player, String researchkey) { return progressResearch(player, researchkey, true); }
/*     */ 
/*     */   
/*     */   public static boolean noFlags = false;
/*     */ 
/*     */   
/*     */   public static boolean progressResearch(EntityPlayer player, String researchkey, boolean sync) {
/* 102 */     IPlayerKnowledge knowledge = ThaumcraftCapabilities.getKnowledge(player);
/* 103 */     if (!knowledge.isResearchComplete(researchkey) && doesPlayerHaveRequisites(player, researchkey)) {
/*     */       
/* 105 */       if (MinecraftForge.EVENT_BUS.post(new ResearchEvent.Research(player, researchkey))) {
/* 106 */         return false;
/*     */       }
/* 108 */       if (!knowledge.isResearchKnown(researchkey)) {
/* 109 */         knowledge.addResearch(researchkey);
/*     */       }
/*     */       
/* 112 */       ResearchEntry re = ResearchCategories.getResearch(researchkey);
/*     */       
/* 114 */       if (re != null) {
/* 115 */         boolean popups = true;
/* 116 */         if (re.getStages() != null) {
/* 117 */           int cs = knowledge.getResearchStage(researchkey);
/* 118 */           ResearchStage currentStage = null;
/* 119 */           if (cs > 0) {
/* 120 */             cs = Math.min(cs, re.getStages().length);
/* 121 */             currentStage = re.getStages()[cs - 1];
/*     */           } 
/*     */           
/* 124 */           if (re.getStages().length == 1 && cs == 0 && re
/* 125 */             .getStages()[0].getCraft() == null && re.getStages()[0].getObtain() == null && re
/* 126 */             .getStages()[0].getKnow() == null && re.getStages()[0].getResearch() == null) {
/* 127 */             cs++;
/*     */           
/*     */           }
/* 130 */           else if (re.getStages().length > 1 && re.getStages().length <= cs + 1 && cs < re.getStages().length && re
/* 131 */             .getStages()[cs].getCraft() == null && re.getStages()[cs].getObtain() == null && re
/* 132 */             .getStages()[cs].getKnow() == null && re.getStages()[cs].getResearch() == null) {
/* 133 */             cs++;
/*     */           } 
/*     */           
/* 136 */           knowledge.setResearchStage(researchkey, Math.min(re.getStages().length + 1, cs + 1));
/* 137 */           popups = (cs >= re.getStages().length);
/*     */           
/* 139 */           int warp = 0;
/* 140 */           if (currentStage != null) {
/* 141 */             warp = currentStage.getWarp();
/*     */           }
/*     */           
/* 144 */           if (popups) {
/* 145 */             cs = Math.min(cs, re.getStages().length);
/* 146 */             currentStage = re.getStages()[cs - 1];
/*     */           } 
/*     */ 
/*     */           
/* 150 */           if (currentStage != null) {
/* 151 */             warp += currentStage.getWarp();
/* 152 */             if (warp > 0 && !ModConfig.CONFIG_MISC.wussMode && 
/* 153 */               !player.field_70170_p.field_72995_K) {
/* 154 */               if (warp > 1) {
/* 155 */                 IPlayerWarp pw = ThaumcraftCapabilities.getWarp(player);
/* 156 */                 int w2 = warp / 2;
/* 157 */                 if (warp - w2 > 0)
/* 158 */                   ThaumcraftApi.internalMethods.addWarpToPlayer(player, warp - w2, IPlayerWarp.EnumWarpType.PERMANENT); 
/* 159 */                 if (w2 > 0)
/* 160 */                   ThaumcraftApi.internalMethods.addWarpToPlayer(player, w2, IPlayerWarp.EnumWarpType.NORMAL); 
/*     */               } else {
/* 162 */                 ThaumcraftApi.internalMethods.addWarpToPlayer(player, warp, IPlayerWarp.EnumWarpType.PERMANENT);
/*     */               } 
/*     */             }
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 169 */         if (popups) {
/* 170 */           if (sync) {
/* 171 */             knowledge.setResearchFlag(researchkey, IPlayerKnowledge.EnumResearchFlag.POPUP);
/* 172 */             if (!noFlags) { knowledge.setResearchFlag(researchkey, IPlayerKnowledge.EnumResearchFlag.RESEARCH); } else { noFlags = false; }
/*     */ 
/*     */             
/* 175 */             if (re.getRewardItem() != null) {
/* 176 */               for (ItemStack rs : re.getRewardItem()) {
/* 177 */                 if (!player.field_71071_by.func_70441_a(rs.func_77946_l())) {
/* 178 */                   player.func_70099_a(rs.func_77946_l(), 1.0F);
/*     */                 }
/*     */               } 
/*     */             }
/* 182 */             if (re.getRewardKnow() != null) {
/* 183 */               for (ResearchStage.Knowledge rk : re.getRewardKnow()) {
/* 184 */                 addKnowledge(player, rk.type, rk.category, rk.type.getProgression() * rk.amount);
/*     */               }
/*     */             }
/*     */           } 
/*     */           
/* 189 */           for (String rc : ResearchCategories.researchCategories.keySet()) {
/* 190 */             for (ResearchEntry ri : (ResearchCategories.getResearchCategory(rc)).research.values()) {
/* 191 */               if (ri == null || ri.getAddenda() == null || !knowledge.isResearchComplete(ri.getKey()))
/* 192 */                 continue;  for (ResearchAddendum addendum : ri.getAddenda()) {
/* 193 */                 if (addendum.getResearch() != null && Arrays.asList(addendum.getResearch()).contains(researchkey)) {
/* 194 */                   TextComponentTranslation textComponentTranslation = new TextComponentTranslation("tc.addaddendum", new Object[] { ri.getLocalizedName() });
/* 195 */                   player.func_145747_a(textComponentTranslation);
/* 196 */                   knowledge.setResearchFlag(ri.getKey(), IPlayerKnowledge.EnumResearchFlag.PAGE);
/*     */ 
/*     */                   
/*     */                   break;
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 207 */       if (re != null && re.getSiblings() != null) {
/* 208 */         for (String sibling : re.getSiblings()) {
/* 209 */           if (!knowledge.isResearchComplete(sibling) && doesPlayerHaveRequisites(player, sibling)) {
/* 210 */             completeResearch(player, sibling, sync);
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/* 215 */       if (sync) {
/* 216 */         syncList.put(player.func_70005_c_(), Boolean.valueOf(true));
/* 217 */         if (re != null) {
/* 218 */           player.func_71023_q(5);
/*     */         }
/*     */       } 
/* 221 */       return true;
/*     */     } 
/* 223 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean doesPlayerHaveRequisites(EntityPlayer player, String key) {
/* 227 */     ResearchEntry ri = ResearchCategories.getResearch(key);
/* 228 */     if (ri == null) return true; 
/* 229 */     String[] parents = ri.getParentsStripped();
/* 230 */     return (parents != null) ? ThaumcraftCapabilities.knowsResearchStrict(player, parents) : 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Aspect getCombinationResult(Aspect aspect1, Aspect aspect2) {
/* 342 */     Collection<Aspect> aspects = Aspect.aspects.values();
/* 343 */     for (Aspect aspect : aspects) {
/* 344 */       if (aspect.getComponents() != null && ((aspect
/* 345 */         .getComponents()[false] == aspect1 && aspect.getComponents()[true] == aspect2) || (aspect
/* 346 */         .getComponents()[false] == aspect2 && aspect.getComponents()[true] == aspect1))) {
/* 347 */         return aspect;
/*     */       }
/*     */     } 
/* 350 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void parseAllResearch() {
/* 355 */     parser = new JsonParser();
/*     */     
/* 357 */     for (ResourceLocation loc : CommonInternals.jsonLocs.values()) {
/* 358 */       String s = "/assets/" + loc.func_110624_b() + "/" + loc.func_110623_a();
/* 359 */       if (!s.endsWith(".json")) s = s + ".json";
/*     */       
/* 361 */       InputStream stream = ResearchManager.class.getResourceAsStream(s);
/*     */       
/* 363 */       if (stream != null)
/*     */         try {
/* 365 */           InputStreamReader reader = new InputStreamReader(stream);
/* 366 */           JsonObject obj = parser.parse(reader).getAsJsonObject();
/* 367 */           JsonArray entries = obj.get("entries").getAsJsonArray();
/* 368 */           int a = 0;
/* 369 */           for (JsonElement element : entries) {
/* 370 */             a++;
/*     */             try {
/* 372 */               JsonObject entry = element.getAsJsonObject();
/* 373 */               ResearchEntry researchEntry = parseResearchJson(entry);
/* 374 */               addResearchToCategory(researchEntry);
/* 375 */             } catch (Exception e) {
/* 376 */               e.printStackTrace();
/* 377 */               Thaumcraft.log.warn("Invalid research entry [" + a + "] found in " + loc.toString());
/* 378 */               a--;
/*     */             } 
/*     */           } 
/* 381 */           Thaumcraft.log.info("Loaded " + a + " research entries from " + loc.toString()); continue;
/* 382 */         } catch (Exception e) {
/* 383 */           Thaumcraft.log.warn("Invalid research file: " + loc.toString());
/*     */           continue;
/*     */         }  
/* 386 */       Thaumcraft.log.warn("Research file not found: " + loc.toString());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 391 */   public static LinkedHashSet<Integer> craftingReferences = new LinkedHashSet();
/*     */ 
/*     */   
/*     */   private static ResearchEntry parseResearchJson(JsonObject obj) throws Exception {
/* 395 */     ResearchEntry entry = new ResearchEntry();
/*     */     
/* 397 */     entry.setKey(obj.getAsJsonPrimitive("key").getAsString());
/* 398 */     if (entry.getKey() == null) throw new Exception("Invalid key in research JSon");
/*     */     
/* 400 */     entry.setName(obj.getAsJsonPrimitive("name").getAsString());
/*     */     
/* 402 */     entry.setCategory(obj.getAsJsonPrimitive("category").getAsString());
/* 403 */     if (entry.getCategory() == null) throw new Exception("Invalid category in research JSon");
/*     */     
/* 405 */     if (obj.has("icons")) {
/* 406 */       String[] icons = arrayJsonToString(obj.get("icons").getAsJsonArray());
/* 407 */       if (icons != null && icons.length > 0) {
/* 408 */         Object[] ir = new Object[icons.length];
/* 409 */         for (int a = 0; a < icons.length; a++) {
/* 410 */           ItemStack stack = parseJSONtoItemStack(icons[a]);
/* 411 */           if (stack != null && !stack.func_190926_b()) {
/* 412 */             ir[a] = stack;
/*     */           }
/* 414 */           else if (icons[a].startsWith("focus")) {
/* 415 */             ir[a] = icons[a];
/*     */           } else {
/* 417 */             ir[a] = new ResourceLocation(icons[a]);
/*     */           } 
/*     */         } 
/* 420 */         entry.setIcons(ir);
/*     */       } 
/*     */     } 
/*     */     
/* 424 */     if (obj.has("parents")) entry.setParents(arrayJsonToString(obj.get("parents").getAsJsonArray())); 
/* 425 */     if (obj.has("siblings")) entry.setSiblings(arrayJsonToString(obj.get("siblings").getAsJsonArray())); 
/* 426 */     if (obj.has("meta")) {
/* 427 */       String[] meta = arrayJsonToString(obj.get("meta").getAsJsonArray());
/* 428 */       if (meta != null && meta.length > 0) {
/* 429 */         ArrayList<ResearchEntry.EnumResearchMeta> metas = new ArrayList<ResearchEntry.EnumResearchMeta>();
/* 430 */         for (String s : meta) {
/* 431 */           ResearchEntry.EnumResearchMeta en = ResearchEntry.EnumResearchMeta.valueOf(s.toUpperCase());
/* 432 */           if (en == null) throw new Exception("Illegal metadata in research JSon"); 
/* 433 */           metas.add(en);
/*     */         } 
/* 435 */         entry.setMeta((EnumResearchMeta[])metas.toArray(new ResearchEntry.EnumResearchMeta[metas.size()]));
/*     */       } 
/*     */     } 
/*     */     
/* 439 */     if (obj.has("location")) {
/* 440 */       Integer[] location = arrayJsonToInt(obj.get("location").getAsJsonArray());
/* 441 */       if (location != null && location.length == 2) {
/* 442 */         entry.setDisplayColumn(location[0].intValue());
/* 443 */         entry.setDisplayRow(location[1].intValue());
/*     */       } 
/*     */     } 
/*     */     
/* 447 */     if (obj.has("reward_item")) entry.setRewardItem(parseJsonItemList(entry.getKey(), arrayJsonToString(obj.get("reward_item").getAsJsonArray())));
/*     */     
/* 449 */     if (obj.has("reward_knowledge")) {
/* 450 */       String[] sl = arrayJsonToString(obj.get("reward_knowledge").getAsJsonArray());
/* 451 */       if (sl != null && sl.length > 0) {
/* 452 */         ArrayList<ResearchStage.Knowledge> kl = new ArrayList<ResearchStage.Knowledge>();
/* 453 */         for (String s : sl) {
/* 454 */           ResearchStage.Knowledge k = ResearchStage.Knowledge.parse(s);
/* 455 */           if (k != null) kl.add(k); 
/*     */         } 
/* 457 */         if (kl.size() > 0) entry.setRewardKnow((Knowledge[])kl.toArray(new ResearchStage.Knowledge[kl.size()]));
/*     */       
/*     */       } 
/*     */     } 
/* 461 */     JsonArray stagesJson = obj.get("stages").getAsJsonArray();
/* 462 */     ArrayList<ResearchStage> stages = new ArrayList<ResearchStage>();
/* 463 */     for (JsonElement element : stagesJson) {
/* 464 */       JsonObject stageObj = element.getAsJsonObject();
/* 465 */       ResearchStage stage = new ResearchStage();
/* 466 */       stage.setText(stageObj.getAsJsonPrimitive("text").getAsString());
/* 467 */       if (stage.getText() == null) throw new Exception("Illegal stage text in research JSon"); 
/* 468 */       if (stageObj.has("recipes")) stage.setRecipes(arrayJsonToResourceLocations(stageObj.get("recipes").getAsJsonArray())); 
/* 469 */       if (stageObj.has("required_item")) stage.setObtain(parseJsonOreList(entry.getKey(), arrayJsonToString(stageObj.get("required_item").getAsJsonArray()))); 
/* 470 */       if (stageObj.has("required_craft")) {
/* 471 */         String[] s1 = arrayJsonToString(stageObj.get("required_craft").getAsJsonArray());
/* 472 */         stage.setCraft(parseJsonOreList(entry.getKey(), s1));
/* 473 */         if (stage.getCraft() != null && stage.getCraft().length > 0) {
/* 474 */           int[] refs = new int[stage.getCraft().length];
/* 475 */           int q = 0;
/* 476 */           for (Object stack : stage.getCraft()) {
/* 477 */             int code = (stack instanceof ItemStack) ? createItemStackHash((ItemStack)stack) : ("oredict:" + (String)stack).hashCode();
/* 478 */             craftingReferences.add(Integer.valueOf(code));
/* 479 */             refs[q] = code;
/* 480 */             q++;
/*     */           } 
/* 482 */           stage.setCraftReference(refs);
/*     */         } 
/*     */       } 
/* 485 */       if (stageObj.has("required_knowledge")) {
/* 486 */         String[] sl = arrayJsonToString(stageObj.get("required_knowledge").getAsJsonArray());
/* 487 */         if (sl != null && sl.length > 0) {
/* 488 */           ArrayList<ResearchStage.Knowledge> kl = new ArrayList<ResearchStage.Knowledge>();
/* 489 */           for (String s : sl) {
/* 490 */             ResearchStage.Knowledge k = ResearchStage.Knowledge.parse(s);
/* 491 */             if (k != null) kl.add(k); 
/*     */           } 
/* 493 */           if (kl.size() > 0) stage.setKnow((Knowledge[])kl.toArray(new ResearchStage.Knowledge[kl.size()])); 
/*     */         } 
/*     */       } 
/* 496 */       if (stageObj.has("required_research")) {
/* 497 */         stage.setResearch(arrayJsonToString(stageObj.get("required_research").getAsJsonArray()));
/* 498 */         if (stage.getResearch() != null && stage.getResearch().length > 0) {
/* 499 */           String[] rKey = new String[stage.getResearch().length];
/* 500 */           String[] rIcn = new String[stage.getResearch().length];
/* 501 */           for (int a = 0; a < stage.getResearch().length; a++) {
/* 502 */             String[] ss = stage.getResearch()[a].split(";");
/* 503 */             rKey[a] = ss[0];
/* 504 */             if (ss.length > 1) {
/* 505 */               rIcn[a] = ss[1];
/*     */             } else {
/* 507 */               rIcn[a] = null;
/*     */             } 
/* 509 */           }  stage.setResearch(rKey);
/* 510 */           stage.setResearchIcon(rIcn);
/*     */         } 
/*     */       } 
/* 513 */       if (stageObj.has("warp")) stage.setWarp(stageObj.getAsJsonPrimitive("warp").getAsInt());
/*     */       
/* 515 */       stages.add(stage);
/*     */     } 
/*     */     
/* 518 */     if (stages.size() > 0) {
/* 519 */       entry.setStages((ResearchStage[])stages.toArray(new ResearchStage[stages.size()]));
/*     */     }
/*     */     
/* 522 */     if (obj.get("addenda") != null) {
/* 523 */       JsonArray addendaJson = obj.get("addenda").getAsJsonArray();
/* 524 */       ArrayList<ResearchAddendum> addenda = new ArrayList<ResearchAddendum>();
/* 525 */       for (JsonElement element : addendaJson) {
/* 526 */         JsonObject addendumObj = element.getAsJsonObject();
/* 527 */         ResearchAddendum addendum = new ResearchAddendum();
/* 528 */         addendum.setText(addendumObj.getAsJsonPrimitive("text").getAsString());
/* 529 */         if (addendum.getText() == null) throw new Exception("Illegal addendum text in research JSon"); 
/* 530 */         if (addendumObj.has("recipes")) addendum.setRecipes(arrayJsonToResourceLocations(addendumObj.get("recipes").getAsJsonArray())); 
/* 531 */         if (addendumObj.has("required_research")) addendum.setResearch(arrayJsonToString(addendumObj.get("required_research").getAsJsonArray())); 
/* 532 */         addenda.add(addendum);
/*     */       } 
/* 534 */       if (addenda.size() > 0) {
/* 535 */         entry.setAddenda((ResearchAddendum[])addenda.toArray(new ResearchAddendum[addenda.size()]));
/*     */       }
/*     */     } 
/*     */     
/* 539 */     return entry;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int createItemStackHash(ItemStack stack) {
/* 545 */     if (stack == null || stack.func_190926_b()) return 0; 
/* 546 */     stack.func_190920_e(1);
/* 547 */     return stack.toString().hashCode();
/*     */   }
/*     */   
/*     */   private static String[] arrayJsonToString(JsonArray jsonArray) {
/* 551 */     ArrayList<String> out = new ArrayList<String>();
/* 552 */     for (JsonElement element : jsonArray) out.add(element.getAsString()); 
/* 553 */     return (out.size() == 0) ? null : (String[])out.toArray(new String[out.size()]);
/*     */   }
/*     */   
/*     */   private static ResourceLocation[] arrayJsonToResourceLocations(JsonArray jsonArray) {
/* 557 */     ArrayList<ResourceLocation> out = new ArrayList<ResourceLocation>();
/* 558 */     for (JsonElement element : jsonArray) out.add(new ResourceLocation(element.getAsString())); 
/* 559 */     return (out.size() == 0) ? null : (ResourceLocation[])out.toArray(new ResourceLocation[out.size()]);
/*     */   }
/*     */   
/*     */   private static Integer[] arrayJsonToInt(JsonArray jsonArray) {
/* 563 */     ArrayList<Integer> out = new ArrayList<Integer>();
/* 564 */     for (JsonElement element : jsonArray) out.add(Integer.valueOf(element.getAsInt())); 
/* 565 */     return (out.size() == 0) ? null : (Integer[])out.toArray(new Integer[out.size()]);
/*     */   }
/*     */   
/*     */   private static ItemStack[] parseJsonItemList(String key, String[] stacks) {
/* 569 */     if (stacks == null || stacks.length == 0) return null; 
/* 570 */     ItemStack[] work = new ItemStack[stacks.length];
/* 571 */     int idx = 0;
/* 572 */     for (String s : stacks) {
/* 573 */       s = s.replace("'", "\"");
/* 574 */       ItemStack stack = parseJSONtoItemStack(s);
/* 575 */       if (stack != null && !stack.func_190926_b()) {
/* 576 */         work[idx] = stack;
/* 577 */         idx++;
/*     */       } 
/*     */     } 
/* 580 */     ItemStack[] out = null;
/* 581 */     if (idx > 0) {
/* 582 */       out = (ItemStack[])Arrays.copyOf(work, idx);
/*     */     }
/* 584 */     return out;
/*     */   }
/*     */   
/*     */   private static Object[] parseJsonOreList(String key, String[] stacks) {
/* 588 */     if (stacks == null || stacks.length == 0) return null; 
/* 589 */     Object[] work = new Object[stacks.length];
/* 590 */     int idx = 0;
/* 591 */     for (String s : stacks) {
/* 592 */       s = s.replace("'", "\"");
/* 593 */       if (s.startsWith("oredict:")) {
/* 594 */         String[] st = s.split(":");
/* 595 */         if (st.length > 1) {
/* 596 */           work[idx] = st[1];
/* 597 */           idx++;
/*     */         } 
/*     */       } else {
/* 600 */         ItemStack stack = parseJSONtoItemStack(s);
/* 601 */         if (stack != null && !stack.func_190926_b()) {
/* 602 */           work[idx] = stack;
/* 603 */           idx++;
/*     */         } 
/*     */       } 
/*     */     } 
/* 607 */     Object[] out = null;
/* 608 */     if (idx > 0) {
/* 609 */       out = (Object[])Arrays.copyOf(work, idx);
/*     */     }
/* 611 */     return out;
/*     */   }
/*     */   
/*     */   public static ItemStack parseJSONtoItemStack(String entry) {
/* 615 */     if (entry == null) return null;
/*     */     
/* 617 */     String[] split = entry.split(";");
/* 618 */     String name = split[0];
/* 619 */     int num = -1;
/* 620 */     int dam = -1;
/* 621 */     String nbt = null;
/* 622 */     for (int a = 1; a < split.length; a++) {
/* 623 */       if (split[a].startsWith("{")) {
/* 624 */         nbt = split[a];
/* 625 */         nbt.replaceAll("'", "\"");
/*     */         break;
/*     */       } 
/* 628 */       int q = -1;
/*     */       try {
/* 630 */         q = Integer.parseInt(split[a]);
/* 631 */       } catch (NumberFormatException e) {}
/* 632 */       if (q >= 0 && num < 0) { num = q; }
/* 633 */       else if (q >= 0 && dam < 0) { dam = q; }
/*     */     
/*     */     } 
/* 636 */     if (num < 0) num = 1; 
/* 637 */     if (dam < 0) dam = 0;
/*     */     
/* 639 */     ItemStack stack = ItemStack.field_190927_a;
/*     */     
/*     */     try {
/* 642 */       Item it = Item.func_111206_d(name);
/* 643 */       if (it != null) {
/* 644 */         stack = new ItemStack(it, num, dam);
/* 645 */         if (nbt != null) {
/* 646 */           stack.func_77982_d(JsonToNBT.func_180713_a(nbt));
/*     */         }
/*     */       } 
/* 649 */     } catch (Exception exception) {}
/*     */     
/* 651 */     return stack;
/*     */   }
/*     */   
/*     */   private static void addResearchToCategory(ResearchEntry ri) {
/* 655 */     ResearchCategory rl = ResearchCategories.getResearchCategory(ri.getCategory());
/* 656 */     if (rl != null && !rl.research.containsKey(ri.getKey())) {
/* 657 */       for (ResearchEntry rr : rl.research.values()) {
/* 658 */         if (rr.getDisplayColumn() == ri.getDisplayColumn() && rr.getDisplayRow() == ri.getDisplayRow()) {
/* 659 */           Thaumcraft.log.warn("Research [" + ri.getKey() + "] not added as it overlaps with existing research [" + rr.getKey() + "] at " + ri.getDisplayColumn() + "," + rr.getDisplayRow());
/*     */           return;
/*     */         } 
/*     */       } 
/* 663 */       rl.research.put(ri.getKey(), ri);
/* 664 */       if (ri.getDisplayColumn() < rl.minDisplayColumn) rl.minDisplayColumn = ri.getDisplayColumn(); 
/* 665 */       if (ri.getDisplayRow() < rl.minDisplayRow) rl.minDisplayRow = ri.getDisplayRow(); 
/* 666 */       if (ri.getDisplayColumn() > rl.maxDisplayColumn) rl.maxDisplayColumn = ri.getDisplayColumn(); 
/* 667 */       if (ri.getDisplayRow() > rl.maxDisplayRow) rl.maxDisplayRow = ri.getDisplayRow(); 
/*     */     } else {
/* 669 */       Thaumcraft.log.warn("Could not add invalid research entry " + ri.getKey());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\research\ResearchManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */