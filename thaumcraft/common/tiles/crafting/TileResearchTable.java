/*     */ package thaumcraft.common.tiles.crafting;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.SPacketUpdateTileEntity;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.capabilities.IPlayerKnowledge;
/*     */ import thaumcraft.api.research.ResearchCategories;
/*     */ import thaumcraft.api.research.ResearchCategory;
/*     */ import thaumcraft.api.research.theorycraft.ITheorycraftAid;
/*     */ import thaumcraft.api.research.theorycraft.ResearchTableData;
/*     */ import thaumcraft.api.research.theorycraft.TheorycraftManager;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import thaumcraft.common.tiles.TileThaumcraftInventory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileResearchTable
/*     */   extends TileThaumcraftInventory
/*     */ {
/*  39 */   public ResearchTableData data = null;
/*     */ 
/*     */   
/*     */   public TileResearchTable() {
/*  43 */     super(2);
/*  44 */     this.syncedSlots = new int[] { 0, 1 };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readSyncNBT(NBTTagCompound nbttagcompound) {
/*  50 */     super.readSyncNBT(nbttagcompound);
/*  51 */     if (nbttagcompound.func_74764_b("note")) {
/*  52 */       this.data = new ResearchTableData(this);
/*  53 */       this.data.deserialize(nbttagcompound.func_74775_l("note"));
/*     */     } else {
/*  55 */       this.data = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeSyncNBT(NBTTagCompound nbttagcompound) {
/*  62 */     if (this.data != null) {
/*  63 */       nbttagcompound.func_74782_a("note", this.data.serialize());
/*     */     } else {
/*  65 */       nbttagcompound.func_82580_o("note");
/*     */     } 
/*  67 */     return super.writeSyncNBT(nbttagcompound);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_190201_b(World worldIn) {
/*  74 */     super.func_190201_b(worldIn);
/*  75 */     if (!func_145830_o()) {
/*  76 */       func_145834_a(worldIn);
/*     */     }
/*     */   }
/*     */   
/*     */   public void startNewTheory(EntityPlayer player, Set<String> mutators) {
/*  81 */     this.data = new ResearchTableData(player, this);
/*  82 */     this.data.initialize(player, mutators);
/*  83 */     syncTile(false);
/*  84 */     func_70296_d();
/*     */   }
/*     */ 
/*     */   
/*     */   public void finishTheory(EntityPlayer player) {
/*  89 */     Comparator<Map.Entry<String, Integer>> valueComparator = (e1, e2) -> ((Integer)e2.getValue()).compareTo((Integer)e1.getValue());
/*  90 */     Map<String, Integer> sortedMap = (Map)this.data.categoryTotals.entrySet().stream().sorted(valueComparator).collect(
/*  91 */         Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, java.util.LinkedHashMap::new));
/*     */     
/*  93 */     int i = 0;
/*  94 */     for (String cat : sortedMap.keySet()) {
/*  95 */       int tot = Math.round(((Integer)sortedMap.get(cat)).intValue() / 100.0F * IPlayerKnowledge.EnumKnowledgeType.THEORY.getProgression());
/*  96 */       if (i > this.data.penaltyStart) {
/*  97 */         tot = (int)Math.max(1.0D, tot * 0.666666667D);
/*     */       }
/*  99 */       ResearchCategory rc = ResearchCategories.getResearchCategory(cat);
/* 100 */       ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.THEORY, rc, tot);
/* 101 */       i++;
/*     */     } 
/*     */     
/* 104 */     this.data = null;
/*     */   }
/*     */   
/*     */   public Set<String> checkSurroundingAids() {
/* 108 */     HashMap<String, ITheorycraftAid> mutators = new HashMap<String, ITheorycraftAid>();
/* 109 */     for (int y = -1; y <= 1; y++) {
/* 110 */       for (int x = -4; x <= 4; x++) {
/* 111 */         for (int z = -4; z <= 4; z++) {
/* 112 */           for (String muk : TheorycraftManager.aids.keySet()) {
/* 113 */             ITheorycraftAid mu = (ITheorycraftAid)TheorycraftManager.aids.get(muk);
/* 114 */             IBlockState state = this.field_145850_b.func_180495_p(func_174877_v().func_177982_a(x, y, z));
/* 115 */             if (mu.getAidObject() instanceof Block) {
/* 116 */               if (state.func_177230_c() == (Block)mu.getAidObject())
/* 117 */                 mutators.put(muk, mu); 
/*     */               continue;
/*     */             } 
/* 120 */             if (mu.getAidObject() instanceof ItemStack) {
/* 121 */               ItemStack is = state.func_177230_c().func_185473_a(func_145831_w(), func_174877_v().func_177982_a(x, y, z), state);
/* 122 */               if (is != null && !is.func_190926_b() && is.func_185136_b((ItemStack)mu.getAidObject()))
/* 123 */                 mutators.put(muk, mu); 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 128 */     }  List<Entity> l = EntityUtils.getEntitiesInRange(func_145831_w(), func_174877_v(), null, Entity.class, 5.0D);
/* 129 */     if (l != null && !l.isEmpty()) {
/* 130 */       for (Entity e : l) {
/* 131 */         for (String muk : TheorycraftManager.aids.keySet()) {
/* 132 */           ITheorycraftAid mu = (ITheorycraftAid)TheorycraftManager.aids.get(muk);
/* 133 */           if (mu.getAidObject() instanceof Class && e.getClass().isAssignableFrom((Class)mu.getAidObject())) {
/* 134 */             mutators.put(muk, mu);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/* 139 */     return mutators.keySet();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean consumeInkFromTable() {
/* 144 */     if (func_70301_a(0).func_77973_b() instanceof thaumcraft.api.items.IScribeTools && func_70301_a(0).func_77952_i() < func_70301_a(0).func_77958_k()) {
/* 145 */       func_70301_a(0).func_77964_b(func_70301_a(0).func_77952_i() + 1);
/* 146 */       syncTile(false);
/* 147 */       func_70296_d();
/* 148 */       return true;
/*     */     } 
/* 150 */     return false;
/*     */   }
/*     */   
/*     */   public boolean consumepaperFromTable() {
/* 154 */     if (func_70301_a(true).func_77973_b() == Items.field_151121_aF && func_70301_a(1).func_190916_E() > 0) {
/* 155 */       func_70298_a(1, 1);
/* 156 */       syncTile(false);
/* 157 */       func_70296_d();
/* 158 */       return true;
/*     */     } 
/* 160 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public String func_70005_c_() { return "Research Table"; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack itemstack) {
/* 171 */     switch (i) { case 0:
/* 172 */         if (itemstack.func_77973_b() instanceof thaumcraft.api.items.IScribeTools) return true;  break;
/* 173 */       case 1: if (itemstack.func_77973_b() == Items.field_151121_aF && itemstack.func_77952_i() == 0) return true;  break; }
/*     */     
/* 175 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
/* 180 */     super.onDataPacket(net, pkt);
/* 181 */     if (this.field_145850_b != null && this.field_145850_b.field_72995_K) {
/* 182 */       syncTile(false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145842_c(int i, int j) {
/* 189 */     if (i == 1) {
/*     */       
/* 191 */       if (this.field_145850_b.field_72995_K) {
/* 192 */         this.field_145850_b.func_184134_a(func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p(), SoundsTC.learn, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
/*     */       }
/* 194 */       return true;
/*     */     } 
/* 196 */     return super.func_145842_c(i, j);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\crafting\TileResearchTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */