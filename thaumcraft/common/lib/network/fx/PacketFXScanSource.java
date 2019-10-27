/*     */ package thaumcraft.common.lib.network.fx;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.fx.particles.FXGeneric;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PacketFXScanSource
/*     */   extends Object
/*     */   implements IMessage, IMessageHandler<PacketFXScanSource, IMessage>
/*     */ {
/*     */   private long loc;
/*     */   private int size;
/*     */   
/*     */   public PacketFXScanSource(BlockPos pos, int size) {
/*  33 */     this.loc = pos.func_177986_g();
/*  34 */     this.size = size;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void toBytes(ByteBuf buffer) {
/*  40 */     buffer.writeLong(this.loc);
/*  41 */     buffer.writeByte(this.size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void fromBytes(ByteBuf buffer) {
/*  46 */     this.loc = buffer.readLong();
/*  47 */     this.size = buffer.readByte();
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IMessage onMessage(final PacketFXScanSource message, MessageContext ctx) {
/*  53 */     Minecraft.func_71410_x().func_152344_a(new Runnable() {
/*  54 */           public void run() { PacketFXScanSource.this.startScan((Minecraft.func_71410_x()).field_71439_g.field_70170_p, BlockPos.func_177969_a(message.loc), message.size); }
/*     */         });
/*  56 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void startScan(World world, BlockPos pos, int r) {
/*  62 */     int range = 4 + r * 4;
/*  63 */     ArrayList<BlockPos> positions = new ArrayList<BlockPos>();
/*     */     
/*  65 */     for (int xx = -range; xx <= range; xx++) {
/*  66 */       for (int yy = -range; yy <= range; yy++) {
/*  67 */         for (int zz = -range; zz <= range; zz++) {
/*  68 */           BlockPos p = pos.func_177982_a(xx, yy, zz);
/*  69 */           if (Utils.isOreBlock(world, p))
/*  70 */             positions.add(p); 
/*     */         } 
/*     */       } 
/*     */     } 
/*  74 */     while (!positions.isEmpty()) {
/*  75 */       BlockPos start = (BlockPos)positions.get(0);
/*  76 */       ArrayList<BlockPos> coll = new ArrayList<BlockPos>();
/*  77 */       coll.add(start);
/*  78 */       positions.remove(0);
/*  79 */       calcGroup(world, start, coll, positions);
/*     */ 
/*     */       
/*  82 */       if (!coll.isEmpty()) {
/*  83 */         int c = getOreColor(world, start);
/*  84 */         double x = 0.0D;
/*  85 */         double y = 0.0D;
/*  86 */         double z = 0.0D;
/*  87 */         for (BlockPos p : coll) {
/*  88 */           x += p.func_177958_n() + 0.5D;
/*  89 */           y += p.func_177956_o() + 0.5D;
/*  90 */           z += p.func_177952_p() + 0.5D;
/*     */         } 
/*     */         
/*  93 */         x /= coll.size();
/*  94 */         y /= coll.size();
/*  95 */         z /= coll.size();
/*     */         
/*  97 */         double dis = Math.sqrt(pos.func_177957_d(x, y, z));
/*  98 */         FXGeneric fb = new FXGeneric(world, x, y, z, 0.0D, 0.0D, 0.0D);
/*  99 */         fb.func_187114_a(44);
/* 100 */         Color cc = new Color(c);
/* 101 */         fb.func_70538_b(cc.getRed() / 255.0F, cc.getGreen() / 255.0F, cc.getBlue() / 255.0F);
/* 102 */         float q = (cc.getRed() / 255.0F + cc.getGreen() / 255.0F + cc.getBlue() / 255.0F) / 3.0F;
/* 103 */         fb.setAlphaF(new float[] { 0.0F, 1.0F, 0.8F, 0.0F });
/* 104 */         fb.setParticles(240, 15, 1);
/* 105 */         fb.setGridSize(16);
/* 106 */         fb.setLoop(true);
/* 107 */         fb.setScale(new float[] { 9.0F });
/* 108 */         fb.setLayer((q < 0.25F) ? 3 : 2);
/* 109 */         fb.setRotationSpeed(0.0F);
/* 110 */         ParticleEngine.addEffectWithDelay(world, fb, (int)(dis * 3.0D));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void calcGroup(World world, BlockPos start, ArrayList<BlockPos> coll, ArrayList<BlockPos> positions) {
/* 116 */     IBlockState bs = world.func_180495_p(start);
/*     */     int x;
/* 118 */     label23: for (x = -1; x <= 1; x++) {
/* 119 */       for (int y = -1; y <= 1; y++) {
/* 120 */         for (int z = -1; z <= 1; z++) {
/* 121 */           BlockPos t = (new BlockPos(start)).func_177982_a(x, y, z);
/* 122 */           IBlockState ts = world.func_180495_p(t);
/* 123 */           if (ts.equals(bs) && positions.contains(t)) {
/* 124 */             positions.remove(t);
/* 125 */             coll.add(t);
/* 126 */             if (!positions.isEmpty()) { calcGroup(world, t, coll, positions); }
/*     */             else
/*     */             { break label23; }
/*     */           
/*     */           } 
/*     */         } 
/*     */       } 
/* 133 */     }  } private int getOreColor(World world, BlockPos pos) { IBlockState bi = world.func_180495_p(pos);
/* 134 */     if (bi.func_177230_c() != Blocks.field_150350_a && bi.func_177230_c() != Blocks.field_150357_h) {
/* 135 */       ItemStack is = BlockUtils.getSilkTouchDrop(bi);
/* 136 */       if (is == null || is.func_190926_b()) {
/* 137 */         int md = bi.func_177230_c().func_176201_c(bi);
/* 138 */         is = new ItemStack(bi.func_177230_c(), 1, md);
/*     */       } 
/* 140 */       if (is == null || is.func_190926_b() || is.func_77973_b() == null) return 12632256; 
/* 141 */       int[] od = OreDictionary.getOreIDs(is);
/* 142 */       if (od != null && od.length > 0)
/* 143 */         for (int id : od) {
/* 144 */           if (OreDictionary.getOreName(id) != null) {
/* 145 */             if (OreDictionary.getOreName(id).toUpperCase().contains("IRON")) return 14200723; 
/* 146 */             if (OreDictionary.getOreName(id).toUpperCase().contains("COAL")) return 1052688; 
/* 147 */             if (OreDictionary.getOreName(id).toUpperCase().contains("REDSTONE")) return 16711680; 
/* 148 */             if (OreDictionary.getOreName(id).toUpperCase().contains("GOLD")) return 16576075; 
/* 149 */             if (OreDictionary.getOreName(id).toUpperCase().contains("LAPIS")) return 1328572; 
/* 150 */             if (OreDictionary.getOreName(id).toUpperCase().contains("DIAMOND")) return 6155509; 
/* 151 */             if (OreDictionary.getOreName(id).toUpperCase().contains("EMERALD")) return 1564002; 
/* 152 */             if (OreDictionary.getOreName(id).toUpperCase().contains("QUARTZ")) return 15064789; 
/* 153 */             if (OreDictionary.getOreName(id).toUpperCase().contains("SILVER")) return 14342653; 
/* 154 */             if (OreDictionary.getOreName(id).toUpperCase().contains("TIN")) return 15724539; 
/* 155 */             if (OreDictionary.getOreName(id).toUpperCase().contains("COPPER")) return 16620629; 
/* 156 */             if (OreDictionary.getOreName(id).toUpperCase().contains("AMBER")) return 16626469; 
/* 157 */             if (OreDictionary.getOreName(id).toUpperCase().contains("CINNABAR")) return 10159368;
/*     */           
/*     */           } 
/*     */         }  
/*     */     } 
/* 162 */     return 12632256; }
/*     */ 
/*     */   
/* 165 */   final int C_QUARTZ = 15064789;
/* 166 */   final int C_IRON = 14200723;
/* 167 */   final int C_LAPIS = 1328572;
/* 168 */   final int C_GOLD = 16576075;
/* 169 */   final int C_DIAMOND = 6155509;
/* 170 */   final int C_EMERALD = 1564002;
/* 171 */   final int C_REDSTONE = 16711680;
/* 172 */   final int C_COAL = 1052688;
/* 173 */   final int C_SILVER = 14342653;
/* 174 */   final int C_TIN = 15724539;
/* 175 */   final int C_COPPER = 16620629;
/* 176 */   final int C_AMBER = 16626469;
/* 177 */   final int C_CINNABAR = 10159368;
/*     */   
/*     */   public PacketFXScanSource() {}
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\fx\PacketFXScanSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */