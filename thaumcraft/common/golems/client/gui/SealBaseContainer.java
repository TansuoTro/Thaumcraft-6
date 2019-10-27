/*     */ package thaumcraft.common.golems.client.gui;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.IContainerListener;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.golems.seals.ISealConfigFilter;
/*     */ import thaumcraft.api.golems.seals.ISealConfigToggles;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ 
/*     */ public class SealBaseContainer extends Container {
/*     */   private World world;
/*     */   ISealEntity seal;
/*     */   EntityPlayer player;
/*     */   InventoryFake temp;
/*     */   int[] categories;
/*     */   int category;
/*     */   InventoryPlayer pinv;
/*     */   int t;
/*     */   private byte lastPriority;
/*     */   private byte lastColor;
/*     */   private int lastAreaX;
/*     */   private int lastAreaY;
/*     */   private int lastAreaZ;
/*     */   
/*  29 */   public SealBaseContainer(InventoryPlayer iinventory, World par2World, ISealEntity seal) { this.seal = null;
/*  30 */     this.player = null;
/*     */ 
/*     */     
/*  33 */     this.category = -1;
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
/*  70 */     this.t = 0; this.world = par2World; this.player = iinventory.field_70458_d; this.pinv = iinventory; this.seal = seal; if (seal.getSeal() instanceof ISealGui) { this.categories = ((ISealGui)seal.getSeal()).getGuiCategories(); }
/*     */     else
/*     */     { this.categories = new int[] { 0 }; }
/*  73 */      setupCategories(); } private void setupFilterInventory() { if (this.seal.getSeal() instanceof ISealConfigFilter) {
/*  74 */       int s = ((ISealConfigFilter)this.seal.getSeal()).getFilterSize();
/*  75 */       int sx = 16 + (s - 1) % 3 * 12;
/*  76 */       int sy = 16 + (s - 1) / 3 * 12;
/*  77 */       int middleX = 88;
/*  78 */       int middleY = 72;
/*  79 */       this.temp = new InventoryFake(((ISealConfigFilter)this.seal.getSeal()).getInv());
/*  80 */       for (int a = 0; a < s; a++) {
/*  81 */         int x = a % 3;
/*  82 */         int y = a / 3;
/*  83 */         func_75146_a(new SlotGhost(this.temp, a, middleX + x * 24 - sx + 8, middleY + y * 24 - sy + 8));
/*  84 */         this.t++;
/*     */       } 
/*     */     }  }
/*     */   void setupCategories() { this.field_75153_a = NonNullList.func_191196_a(); this.field_75151_b = Lists.newArrayList(); this.t = 0; if (this.category < 0)
/*     */       this.category = this.categories[0];  switch (this.category) { case 1:
/*     */         setupFilterInventory(); break; }
/*  90 */      bindPlayerInventory(this.pinv); } protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) { for (int i = 0; i < 3; i++) {
/*  91 */       for (int j = 0; j < 9; j++) {
/*  92 */         func_75146_a(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 150 + i * 18));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  97 */     for (int i = 0; i < 9; i++) {
/*  98 */       func_75146_a(new Slot(inventoryPlayer, i, 8 + i * 18, 208));
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   public boolean func_75145_c(EntityPlayer var1) { return true; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75140_a(EntityPlayer player, int par2) {
/* 109 */     if (par2 >= 0 && par2 < this.categories.length) {
/* 110 */       this.category = this.categories[par2];
/* 111 */       setupCategories();
/* 112 */       return true;
/*     */     } 
/*     */     
/* 115 */     if (this.category == 3 && this.seal.getSeal() instanceof ISealConfigToggles && par2 >= 30 && par2 < 30 + ((ISealConfigToggles)this.seal
/* 116 */       .getSeal()).getToggles().length) {
/* 117 */       ISealConfigToggles cp = (ISealConfigToggles)this.seal.getSeal();
/* 118 */       cp.setToggle(par2 - 30, true);
/* 119 */       return true;
/*     */     } 
/*     */     
/* 122 */     if (this.category == 3 && this.seal.getSeal() instanceof ISealConfigToggles && par2 >= 60 && par2 < 60 + ((ISealConfigToggles)this.seal
/* 123 */       .getSeal()).getToggles().length) {
/* 124 */       ISealConfigToggles cp = (ISealConfigToggles)this.seal.getSeal();
/* 125 */       cp.setToggle(par2 - 60, false);
/* 126 */       return true;
/*     */     } 
/*     */     
/* 129 */     if (this.category == 0 && par2 >= 25 && par2 <= 26) {
/* 130 */       this.seal.setLocked((par2 == 25));
/* 131 */       return true;
/*     */     } 
/*     */     
/* 134 */     if (par2 >= 27 && par2 <= 28) {
/* 135 */       this.seal.setRedstoneSensitive((par2 == 27));
/* 136 */       return true;
/*     */     } 
/*     */     
/* 139 */     if (this.category == 1 && this.seal.getSeal() instanceof ISealConfigFilter && par2 >= 20 && par2 <= 21) {
/* 140 */       ISealConfigFilter cp = (ISealConfigFilter)this.seal.getSeal();
/* 141 */       cp.setBlacklist((par2 == 20));
/* 142 */       return true;
/*     */     } 
/*     */     
/* 145 */     if (par2 == 80 && this.seal.getPriority() > -5) {
/* 146 */       this.seal.setPriority((byte)(this.seal.getPriority() - 1));
/* 147 */       return true;
/*     */     } 
/* 149 */     if (par2 == 81 && this.seal.getPriority() < 5) {
/* 150 */       this.seal.setPriority((byte)(this.seal.getPriority() + 1));
/* 151 */       return true;
/*     */     } 
/*     */     
/* 154 */     if (par2 == 82 && this.seal.getColor() > 0) {
/* 155 */       this.seal.setColor((byte)(this.seal.getColor() - 1));
/* 156 */       return true;
/*     */     } 
/* 158 */     if (par2 == 83 && this.seal.getColor() < 16) {
/* 159 */       this.seal.setColor((byte)(this.seal.getColor() + 1));
/* 160 */       return true;
/*     */     } 
/*     */     
/* 163 */     if (this.seal.getSeal() instanceof thaumcraft.api.golems.seals.ISealConfigArea) {
/* 164 */       if (par2 == 90 && this.seal.getArea().func_177956_o() > 1) {
/* 165 */         this.seal.setArea(this.seal.getArea().func_177982_a(0, -1, 0));
/* 166 */         return true;
/*     */       } 
/* 168 */       if (par2 == 91 && this.seal.getArea().func_177956_o() < 8) {
/* 169 */         this.seal.setArea(this.seal.getArea().func_177982_a(0, 1, 0));
/* 170 */         return true;
/*     */       } 
/*     */       
/* 173 */       if (par2 == 92 && this.seal.getArea().func_177958_n() > 1) {
/* 174 */         this.seal.setArea(this.seal.getArea().func_177982_a(-1, 0, 0));
/* 175 */         return true;
/*     */       } 
/* 177 */       if (par2 == 93 && this.seal.getArea().func_177958_n() < 8) {
/* 178 */         this.seal.setArea(this.seal.getArea().func_177982_a(1, 0, 0));
/* 179 */         return true;
/*     */       } 
/*     */       
/* 182 */       if (par2 == 94 && this.seal.getArea().func_177952_p() > 1) {
/* 183 */         this.seal.setArea(this.seal.getArea().func_177982_a(0, 0, -1));
/* 184 */         return true;
/*     */       } 
/* 186 */       if (par2 == 95 && this.seal.getArea().func_177952_p() < 8) {
/* 187 */         this.seal.setArea(this.seal.getArea().func_177982_a(0, 0, 1));
/* 188 */         return true;
/*     */       } 
/*     */     } 
/* 191 */     return super.func_75140_a(player, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75132_a(IContainerListener crafting) {
/* 199 */     super.func_75132_a(crafting);
/* 200 */     crafting.func_71112_a(this, 0, this.seal.getPriority());
/* 201 */     crafting.func_71112_a(this, 4, this.seal.getColor());
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
/*     */   public void func_75142_b() {
/* 213 */     super.func_75142_b();
/*     */     
/* 215 */     for (int i = 0; i < this.field_75149_d.size(); i++) {
/*     */       
/* 217 */       IContainerListener icrafting = (IContainerListener)this.field_75149_d.get(i);
/*     */       
/* 219 */       if (this.lastPriority != this.seal.getPriority())
/*     */       {
/* 221 */         icrafting.func_71112_a(this, 0, this.seal.getPriority());
/*     */       }
/*     */       
/* 224 */       if (this.lastAreaX != this.seal.getArea().func_177958_n()) icrafting.func_71112_a(this, 1, this.seal.getArea().func_177958_n()); 
/* 225 */       if (this.lastAreaY != this.seal.getArea().func_177956_o()) icrafting.func_71112_a(this, 2, this.seal.getArea().func_177956_o()); 
/* 226 */       if (this.lastAreaZ != this.seal.getArea().func_177952_p()) icrafting.func_71112_a(this, 3, this.seal.getArea().func_177952_p());
/*     */       
/* 228 */       if (this.lastColor != this.seal.getColor())
/*     */       {
/* 230 */         icrafting.func_71112_a(this, 4, this.seal.getColor());
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 235 */     this.lastPriority = this.seal.getPriority();
/* 236 */     this.lastColor = this.seal.getColor();
/* 237 */     this.lastAreaX = this.seal.getArea().func_177958_n();
/* 238 */     this.lastAreaY = this.seal.getArea().func_177956_o();
/* 239 */     this.lastAreaZ = this.seal.getArea().func_177952_p();
/*     */     
/* 241 */     if (this.seal.getSeal() instanceof ISealConfigFilter && this.temp != null) {
/* 242 */       for (int a = 0; a < this.temp.func_70302_i_(); a++) {
/* 243 */         ((ISealConfigFilter)this.seal.getSeal()).setFilterSlot(a, this.temp.func_70301_a(a));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_75137_b(int par1, int par2) {
/* 256 */     if (par1 == 0) this.seal.setPriority((byte)par2); 
/* 257 */     if (par1 == 1) this.seal.setArea(new BlockPos(par2, this.seal.getArea().func_177956_o(), this.seal.getArea().func_177952_p())); 
/* 258 */     if (par1 == 2) this.seal.setArea(new BlockPos(this.seal.getArea().func_177958_n(), par2, this.seal.getArea().func_177952_p())); 
/* 259 */     if (par1 == 3) this.seal.setArea(new BlockPos(this.seal.getArea().func_177958_n(), this.seal.getArea().func_177956_o(), par2)); 
/* 260 */     if (par1 == 4) this.seal.setColor((byte)par2);
/*     */   
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
/*     */   public ItemStack func_184996_a(int slotId, int clickedButton, ClickType mode, EntityPlayer playerIn) { // Byte code:
/*     */     //   0: iload_1
/*     */     //   1: iflt -> 773
/*     */     //   4: aload_0
/*     */     //   5: getfield field_75151_b : Ljava/util/List;
/*     */     //   8: iload_1
/*     */     //   9: invokeinterface get : (I)Ljava/lang/Object;
/*     */     //   14: checkcast net/minecraft/inventory/Slot
/*     */     //   17: astore #5
/*     */     //   19: aload #4
/*     */     //   21: getfield field_71071_by : Lnet/minecraft/entity/player/InventoryPlayer;
/*     */     //   24: astore #6
/*     */     //   26: getstatic net/minecraft/item/ItemStack.field_190927_a : Lnet/minecraft/item/ItemStack;
/*     */     //   29: astore #7
/*     */     //   31: aload #6
/*     */     //   33: invokevirtual func_70445_o : ()Lnet/minecraft/item/ItemStack;
/*     */     //   36: ifnull -> 60
/*     */     //   39: aload #6
/*     */     //   41: invokevirtual func_70445_o : ()Lnet/minecraft/item/ItemStack;
/*     */     //   44: invokevirtual func_190926_b : ()Z
/*     */     //   47: ifne -> 60
/*     */     //   50: aload #6
/*     */     //   52: invokevirtual func_70445_o : ()Lnet/minecraft/item/ItemStack;
/*     */     //   55: invokevirtual func_77946_l : ()Lnet/minecraft/item/ItemStack;
/*     */     //   58: astore #7
/*     */     //   60: aload #5
/*     */     //   62: ifnull -> 773
/*     */     //   65: aload #5
/*     */     //   67: instanceof thaumcraft/common/container/slot/SlotGhost
/*     */     //   70: ifeq -> 773
/*     */     //   73: aload_0
/*     */     //   74: getfield seal : Lthaumcraft/api/golems/seals/ISealEntity;
/*     */     //   77: invokeinterface getSeal : ()Lthaumcraft/api/golems/seals/ISeal;
/*     */     //   82: checkcast thaumcraft/api/golems/seals/ISealConfigFilter
/*     */     //   85: invokeinterface hasStacksizeLimiters : ()Z
/*     */     //   90: istore #8
/*     */     //   92: aload #4
/*     */     //   94: getfield field_70170_p : Lnet/minecraft/world/World;
/*     */     //   97: getfield field_72995_K : Z
/*     */     //   100: ifeq -> 118
/*     */     //   103: getstatic thaumcraft/Thaumcraft.instance : Lthaumcraft/Thaumcraft;
/*     */     //   106: pop
/*     */     //   107: getstatic thaumcraft/Thaumcraft.proxy : Lthaumcraft/proxies/IProxy;
/*     */     //   110: invokeinterface getSingleplayer : ()Z
/*     */     //   115: ifne -> 769
/*     */     //   118: iload_2
/*     */     //   119: iconst_1
/*     */     //   120: if_icmpne -> 491
/*     */     //   123: iload #8
/*     */     //   125: ifne -> 162
/*     */     //   128: aload #5
/*     */     //   130: getstatic net/minecraft/item/ItemStack.field_190927_a : Lnet/minecraft/item/ItemStack;
/*     */     //   133: invokevirtual func_75215_d : (Lnet/minecraft/item/ItemStack;)V
/*     */     //   136: aload_0
/*     */     //   137: getfield seal : Lthaumcraft/api/golems/seals/ISealEntity;
/*     */     //   140: invokeinterface getSeal : ()Lthaumcraft/api/golems/seals/ISeal;
/*     */     //   145: checkcast thaumcraft/api/golems/seals/ISealConfigFilter
/*     */     //   148: aload #5
/*     */     //   150: getfield field_75222_d : I
/*     */     //   153: iconst_0
/*     */     //   154: invokeinterface setFilterSlotSize : (II)V
/*     */     //   159: goto -> 737
/*     */     //   162: aload #7
/*     */     //   164: getstatic net/minecraft/item/ItemStack.field_190927_a : Lnet/minecraft/item/ItemStack;
/*     */     //   167: if_acmpne -> 295
/*     */     //   170: aload #5
/*     */     //   172: invokevirtual func_75216_d : ()Z
/*     */     //   175: ifeq -> 737
/*     */     //   178: aload_0
/*     */     //   179: getfield seal : Lthaumcraft/api/golems/seals/ISealEntity;
/*     */     //   182: invokeinterface getSeal : ()Lthaumcraft/api/golems/seals/ISeal;
/*     */     //   187: checkcast thaumcraft/api/golems/seals/ISealConfigFilter
/*     */     //   190: aload #5
/*     */     //   192: getfield field_75222_d : I
/*     */     //   195: aload_0
/*     */     //   196: getfield seal : Lthaumcraft/api/golems/seals/ISealEntity;
/*     */     //   199: invokeinterface getSeal : ()Lthaumcraft/api/golems/seals/ISeal;
/*     */     //   204: checkcast thaumcraft/api/golems/seals/ISealConfigFilter
/*     */     //   207: aload #5
/*     */     //   209: getfield field_75222_d : I
/*     */     //   212: invokeinterface getFilterSlotSize : (I)I
/*     */     //   217: aload_3
/*     */     //   218: getstatic net/minecraft/inventory/ClickType.QUICK_MOVE : Lnet/minecraft/inventory/ClickType;
/*     */     //   221: if_acmpne -> 229
/*     */     //   224: bipush #10
/*     */     //   226: goto -> 230
/*     */     //   229: iconst_1
/*     */     //   230: isub
/*     */     //   231: invokeinterface setFilterSlotSize : (II)V
/*     */     //   236: aload_0
/*     */     //   237: getfield seal : Lthaumcraft/api/golems/seals/ISealEntity;
/*     */     //   240: invokeinterface getSeal : ()Lthaumcraft/api/golems/seals/ISeal;
/*     */     //   245: checkcast thaumcraft/api/golems/seals/ISealConfigFilter
/*     */     //   248: aload #5
/*     */     //   250: getfield field_75222_d : I
/*     */     //   253: invokeinterface getFilterSlotSize : (I)I
/*     */     //   258: ifge -> 737
/*     */     //   261: aload #5
/*     */     //   263: getstatic net/minecraft/item/ItemStack.field_190927_a : Lnet/minecraft/item/ItemStack;
/*     */     //   266: invokevirtual func_75215_d : (Lnet/minecraft/item/ItemStack;)V
/*     */     //   269: aload_0
/*     */     //   270: getfield seal : Lthaumcraft/api/golems/seals/ISealEntity;
/*     */     //   273: invokeinterface getSeal : ()Lthaumcraft/api/golems/seals/ISeal;
/*     */     //   278: checkcast thaumcraft/api/golems/seals/ISealConfigFilter
/*     */     //   281: aload #5
/*     */     //   283: getfield field_75222_d : I
/*     */     //   286: iconst_0
/*     */     //   287: invokeinterface setFilterSlotSize : (II)V
/*     */     //   292: goto -> 737
/*     */     //   295: aload #5
/*     */     //   297: invokevirtual func_75216_d : ()Z
/*     */     //   300: ifeq -> 348
/*     */     //   303: aload #5
/*     */     //   305: invokevirtual func_75211_c : ()Lnet/minecraft/item/ItemStack;
/*     */     //   308: invokevirtual func_190916_E : ()I
/*     */     //   311: ifne -> 348
/*     */     //   314: aload #5
/*     */     //   316: getstatic net/minecraft/item/ItemStack.field_190927_a : Lnet/minecraft/item/ItemStack;
/*     */     //   319: invokevirtual func_75215_d : (Lnet/minecraft/item/ItemStack;)V
/*     */     //   322: aload_0
/*     */     //   323: getfield seal : Lthaumcraft/api/golems/seals/ISealEntity;
/*     */     //   326: invokeinterface getSeal : ()Lthaumcraft/api/golems/seals/ISeal;
/*     */     //   331: checkcast thaumcraft/api/golems/seals/ISealConfigFilter
/*     */     //   334: aload #5
/*     */     //   336: getfield field_75222_d : I
/*     */     //   339: iconst_0
/*     */     //   340: invokeinterface setFilterSlotSize : (II)V
/*     */     //   345: goto -> 737
/*     */     //   348: aload #5
/*     */     //   350: invokevirtual func_75216_d : ()Z
/*     */     //   353: ifeq -> 737
/*     */     //   356: aload #7
/*     */     //   358: aload #5
/*     */     //   360: invokevirtual func_75211_c : ()Lnet/minecraft/item/ItemStack;
/*     */     //   363: invokestatic func_179545_c : (Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z
/*     */     //   366: ifeq -> 737
/*     */     //   369: aload #7
/*     */     //   371: aload #5
/*     */     //   373: invokevirtual func_75211_c : ()Lnet/minecraft/item/ItemStack;
/*     */     //   376: invokestatic func_77970_a : (Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z
/*     */     //   379: ifeq -> 737
/*     */     //   382: aload_0
/*     */     //   383: getfield seal : Lthaumcraft/api/golems/seals/ISealEntity;
/*     */     //   386: invokeinterface getSeal : ()Lthaumcraft/api/golems/seals/ISeal;
/*     */     //   391: checkcast thaumcraft/api/golems/seals/ISealConfigFilter
/*     */     //   394: aload #5
/*     */     //   396: getfield field_75222_d : I
/*     */     //   399: aload_0
/*     */     //   400: getfield seal : Lthaumcraft/api/golems/seals/ISealEntity;
/*     */     //   403: invokeinterface getSeal : ()Lthaumcraft/api/golems/seals/ISeal;
/*     */     //   408: checkcast thaumcraft/api/golems/seals/ISealConfigFilter
/*     */     //   411: aload #5
/*     */     //   413: getfield field_75222_d : I
/*     */     //   416: invokeinterface getFilterSlotSize : (I)I
/*     */     //   421: aload #7
/*     */     //   423: invokevirtual func_190916_E : ()I
/*     */     //   426: isub
/*     */     //   427: invokeinterface setFilterSlotSize : (II)V
/*     */     //   432: aload_0
/*     */     //   433: getfield seal : Lthaumcraft/api/golems/seals/ISealEntity;
/*     */     //   436: invokeinterface getSeal : ()Lthaumcraft/api/golems/seals/ISeal;
/*     */     //   441: checkcast thaumcraft/api/golems/seals/ISealConfigFilter
/*     */     //   444: aload #5
/*     */     //   446: getfield field_75222_d : I
/*     */     //   449: invokeinterface getFilterSlotSize : (I)I
/*     */     //   454: ifge -> 737
/*     */     //   457: aload #5
/*     */     //   459: getstatic net/minecraft/item/ItemStack.field_190927_a : Lnet/minecraft/item/ItemStack;
/*     */     //   462: invokevirtual func_75215_d : (Lnet/minecraft/item/ItemStack;)V
/*     */     //   465: aload_0
/*     */     //   466: getfield seal : Lthaumcraft/api/golems/seals/ISealEntity;
/*     */     //   469: invokeinterface getSeal : ()Lthaumcraft/api/golems/seals/ISeal;
/*     */     //   474: checkcast thaumcraft/api/golems/seals/ISealConfigFilter
/*     */     //   477: aload #5
/*     */     //   479: getfield field_75222_d : I
/*     */     //   482: iconst_0
/*     */     //   483: invokeinterface setFilterSlotSize : (II)V
/*     */     //   488: goto -> 737
/*     */     //   491: aload #7
/*     */     //   493: getstatic net/minecraft/item/ItemStack.field_190927_a : Lnet/minecraft/item/ItemStack;
/*     */     //   496: if_acmpne -> 573
/*     */     //   499: iload #8
/*     */     //   501: ifeq -> 737
/*     */     //   504: aload #5
/*     */     //   506: invokevirtual func_75216_d : ()Z
/*     */     //   509: ifeq -> 737
/*     */     //   512: aload_0
/*     */     //   513: getfield seal : Lthaumcraft/api/golems/seals/ISealEntity;
/*     */     //   516: invokeinterface getSeal : ()Lthaumcraft/api/golems/seals/ISeal;
/*     */     //   521: checkcast thaumcraft/api/golems/seals/ISealConfigFilter
/*     */     //   524: aload #5
/*     */     //   526: getfield field_75222_d : I
/*     */     //   529: aload_0
/*     */     //   530: getfield seal : Lthaumcraft/api/golems/seals/ISealEntity;
/*     */     //   533: invokeinterface getSeal : ()Lthaumcraft/api/golems/seals/ISeal;
/*     */     //   538: checkcast thaumcraft/api/golems/seals/ISealConfigFilter
/*     */     //   541: aload #5
/*     */     //   543: getfield field_75222_d : I
/*     */     //   546: invokeinterface getFilterSlotSize : (I)I
/*     */     //   551: aload_3
/*     */     //   552: getstatic net/minecraft/inventory/ClickType.QUICK_MOVE : Lnet/minecraft/inventory/ClickType;
/*     */     //   555: if_acmpne -> 563
/*     */     //   558: bipush #10
/*     */     //   560: goto -> 564
/*     */     //   563: iconst_1
/*     */     //   564: iadd
/*     */     //   565: invokeinterface setFilterSlotSize : (II)V
/*     */     //   570: goto -> 737
/*     */     //   573: iload #8
/*     */     //   575: ifne -> 610
/*     */     //   578: aload #7
/*     */     //   580: iconst_1
/*     */     //   581: invokevirtual func_190920_e : (I)V
/*     */     //   584: aload_0
/*     */     //   585: getfield seal : Lthaumcraft/api/golems/seals/ISealEntity;
/*     */     //   588: invokeinterface getSeal : ()Lthaumcraft/api/golems/seals/ISeal;
/*     */     //   593: checkcast thaumcraft/api/golems/seals/ISealConfigFilter
/*     */     //   596: aload #5
/*     */     //   598: getfield field_75222_d : I
/*     */     //   601: iconst_0
/*     */     //   602: invokeinterface setFilterSlotSize : (II)V
/*     */     //   607: goto -> 730
/*     */     //   610: aload #7
/*     */     //   612: invokevirtual func_190916_E : ()I
/*     */     //   615: istore #9
/*     */     //   617: aload #7
/*     */     //   619: iconst_1
/*     */     //   620: invokevirtual func_190920_e : (I)V
/*     */     //   623: aload #5
/*     */     //   625: invokevirtual func_75216_d : ()Z
/*     */     //   628: ifeq -> 707
/*     */     //   631: aload #7
/*     */     //   633: aload #5
/*     */     //   635: invokevirtual func_75211_c : ()Lnet/minecraft/item/ItemStack;
/*     */     //   638: invokestatic func_179545_c : (Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z
/*     */     //   641: ifeq -> 707
/*     */     //   644: aload #7
/*     */     //   646: aload #5
/*     */     //   648: invokevirtual func_75211_c : ()Lnet/minecraft/item/ItemStack;
/*     */     //   651: invokestatic func_77970_a : (Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z
/*     */     //   654: ifeq -> 707
/*     */     //   657: aload_0
/*     */     //   658: getfield seal : Lthaumcraft/api/golems/seals/ISealEntity;
/*     */     //   661: invokeinterface getSeal : ()Lthaumcraft/api/golems/seals/ISeal;
/*     */     //   666: checkcast thaumcraft/api/golems/seals/ISealConfigFilter
/*     */     //   669: aload #5
/*     */     //   671: getfield field_75222_d : I
/*     */     //   674: aload_0
/*     */     //   675: getfield seal : Lthaumcraft/api/golems/seals/ISealEntity;
/*     */     //   678: invokeinterface getSeal : ()Lthaumcraft/api/golems/seals/ISeal;
/*     */     //   683: checkcast thaumcraft/api/golems/seals/ISealConfigFilter
/*     */     //   686: aload #5
/*     */     //   688: getfield field_75222_d : I
/*     */     //   691: invokeinterface getFilterSlotSize : (I)I
/*     */     //   696: iload #9
/*     */     //   698: iadd
/*     */     //   699: invokeinterface setFilterSlotSize : (II)V
/*     */     //   704: goto -> 730
/*     */     //   707: aload_0
/*     */     //   708: getfield seal : Lthaumcraft/api/golems/seals/ISealEntity;
/*     */     //   711: invokeinterface getSeal : ()Lthaumcraft/api/golems/seals/ISeal;
/*     */     //   716: checkcast thaumcraft/api/golems/seals/ISealConfigFilter
/*     */     //   719: aload #5
/*     */     //   721: getfield field_75222_d : I
/*     */     //   724: iconst_0
/*     */     //   725: invokeinterface setFilterSlotSize : (II)V
/*     */     //   730: aload #5
/*     */     //   732: aload #7
/*     */     //   734: invokevirtual func_75215_d : (Lnet/minecraft/item/ItemStack;)V
/*     */     //   737: aload #5
/*     */     //   739: invokevirtual func_75216_d : ()Z
/*     */     //   742: ifeq -> 765
/*     */     //   745: aload #5
/*     */     //   747: invokevirtual func_75211_c : ()Lnet/minecraft/item/ItemStack;
/*     */     //   750: invokevirtual func_190916_E : ()I
/*     */     //   753: ifge -> 765
/*     */     //   756: aload #5
/*     */     //   758: invokevirtual func_75211_c : ()Lnet/minecraft/item/ItemStack;
/*     */     //   761: iconst_0
/*     */     //   762: invokevirtual func_190920_e : (I)V
/*     */     //   765: aload_0
/*     */     //   766: invokevirtual func_75142_b : ()V
/*     */     //   769: getstatic net/minecraft/item/ItemStack.field_190927_a : Lnet/minecraft/item/ItemStack;
/*     */     //   772: areturn
/*     */     //   773: aload_0
/*     */     //   774: iload_1
/*     */     //   775: iload_2
/*     */     //   776: aload_3
/*     */     //   777: aload #4
/*     */     //   779: invokespecial func_184996_a : (IILnet/minecraft/inventory/ClickType;Lnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/item/ItemStack;
/*     */     //   782: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #265	-> 0
/*     */     //   #266	-> 4
/*     */     //   #267	-> 19
/*     */     //   #268	-> 26
/*     */     //   #269	-> 31
/*     */     //   #271	-> 60
/*     */     //   #272	-> 73
/*     */     //   #273	-> 92
/*     */     //   #274	-> 118
/*     */     //   #275	-> 123
/*     */     //   #276	-> 128
/*     */     //   #277	-> 136
/*     */     //   #279	-> 162
/*     */     //   #284	-> 170
/*     */     //   #285	-> 178
/*     */     //   #286	-> 236
/*     */     //   #287	-> 261
/*     */     //   #288	-> 269
/*     */     //   #293	-> 295
/*     */     //   #294	-> 314
/*     */     //   #295	-> 322
/*     */     //   #298	-> 348
/*     */     //   #299	-> 382
/*     */     //   #300	-> 432
/*     */     //   #301	-> 457
/*     */     //   #302	-> 465
/*     */     //   #308	-> 491
/*     */     //   #309	-> 499
/*     */     //   #310	-> 512
/*     */     //   #313	-> 573
/*     */     //   #314	-> 578
/*     */     //   #315	-> 584
/*     */     //   #317	-> 610
/*     */     //   #318	-> 617
/*     */     //   #319	-> 623
/*     */     //   #320	-> 657
/*     */     //   #322	-> 707
/*     */     //   #325	-> 730
/*     */     //   #329	-> 737
/*     */     //   #330	-> 756
/*     */     //   #333	-> 765
/*     */     //   #336	-> 769
/*     */     //   #340	-> 773
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   617	113	9	os	I
/*     */     //   92	681	8	filter	Z
/*     */     //   19	754	5	slot	Lnet/minecraft/inventory/Slot;
/*     */     //   26	747	6	inventoryplayer	Lnet/minecraft/entity/player/InventoryPlayer;
/*     */     //   31	742	7	ic	Lnet/minecraft/item/ItemStack;
/*     */     //   0	783	0	this	Lthaumcraft/common/golems/client/gui/SealBaseContainer;
/*     */     //   0	783	1	slotId	I
/*     */     //   0	783	2	clickedButton	I
/*     */     //   0	783	3	mode	Lnet/minecraft/inventory/ClickType;
/*     */     //   0	783	4	playerIn	Lnet/minecraft/entity/player/EntityPlayer; }
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
/*     */   public ItemStack func_82846_b(EntityPlayer player, int par2) {
/* 346 */     ItemStack itemstack = null;
/* 347 */     Slot slot = (Slot)this.field_75151_b.get(par2);
/*     */     
/* 349 */     if (slot != null && slot.func_75216_d()) {
/*     */       
/* 351 */       ItemStack itemstack1 = slot.func_75211_c();
/* 352 */       itemstack = itemstack1.func_77946_l();
/*     */       
/* 354 */       if (itemstack1.func_190916_E() == 0) {
/*     */         
/* 356 */         slot.func_75215_d(ItemStack.field_190927_a);
/*     */       }
/*     */       else {
/*     */         
/* 360 */         slot.func_75218_e();
/*     */       } 
/*     */       
/* 363 */       if (itemstack1.func_190916_E() == itemstack.func_190916_E())
/*     */       {
/* 365 */         return ItemStack.field_190927_a;
/*     */       }
/*     */       
/* 368 */       slot.func_190901_a(player, itemstack1);
/*     */     } 
/*     */     
/* 371 */     return itemstack;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\client\gui\SealBaseContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */