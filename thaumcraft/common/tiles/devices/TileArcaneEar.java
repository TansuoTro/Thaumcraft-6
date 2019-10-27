/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.WeakHashMap;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.blocks.IBlockEnabled;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileArcaneEar
/*     */   extends TileEntity
/*     */   implements ITickable
/*     */ {
/*  24 */   public byte note = 0;
/*  25 */   public byte tone = 0;
/*  26 */   public int redstoneSignal = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound func_189515_b(NBTTagCompound par1NBTTagCompound) {
/*  33 */     super.func_189515_b(par1NBTTagCompound);
/*  34 */     par1NBTTagCompound.func_74774_a("note", this.note);
/*  35 */     par1NBTTagCompound.func_74774_a("tone", this.tone);
/*  36 */     return par1NBTTagCompound;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound par1NBTTagCompound) {
/*  44 */     super.func_145839_a(par1NBTTagCompound);
/*  45 */     this.note = par1NBTTagCompound.func_74771_c("note");
/*  46 */     this.tone = par1NBTTagCompound.func_74771_c("tone");
/*     */     
/*  48 */     if (this.note < 0)
/*     */     {
/*  50 */       this.note = 0;
/*     */     }
/*     */     
/*  53 */     if (this.note > 24)
/*     */     {
/*  55 */       this.note = 24;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73660_a() {
/*  62 */     if (!this.field_145850_b.field_72995_K) {
/*  63 */       if (this.redstoneSignal > 0) {
/*  64 */         this.redstoneSignal--;
/*  65 */         if (this.redstoneSignal == 0) {
/*  66 */           EnumFacing facing = BlockStateUtils.getFacing(func_145832_p()).func_176734_d();
/*  67 */           TileEntity tileentity = this.field_145850_b.func_175625_s(this.field_174879_c);
/*  68 */           this.field_145850_b.func_180501_a(this.field_174879_c, this.field_145850_b.func_180495_p(this.field_174879_c).func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(false)), 3);
/*  69 */           if (tileentity != null) {
/*     */             
/*  71 */             tileentity.func_145829_t();
/*  72 */             this.field_145850_b.func_175690_a(this.field_174879_c, tileentity);
/*     */           } 
/*  74 */           this.field_145850_b.func_175685_c(this.field_174879_c, func_145838_q(), true);
/*  75 */           this.field_145850_b.func_175685_c(this.field_174879_c.func_177972_a(facing), func_145838_q(), true);
/*  76 */           IBlockState state = this.field_145850_b.func_180495_p(this.field_174879_c);
/*  77 */           this.field_145850_b.markAndNotifyBlock(this.field_174879_c, this.field_145850_b.func_175726_f(this.field_174879_c), state, state, 3);
/*     */         } 
/*     */       } 
/*  80 */       ArrayList<Integer[]> nbe = (ArrayList)noteBlockEvents.get(Integer.valueOf(this.field_145850_b.field_73011_w.getDimension()));
/*  81 */       if (nbe != null) {
/*  82 */         for (Integer[] dat : nbe) {
/*  83 */           if (dat[3].intValue() == this.tone && dat[4].intValue() == this.note && 
/*  84 */             func_145835_a(dat[0].intValue() + 0.5D, dat[1].intValue() + 0.5D, dat[2].intValue() + 0.5D) <= 4096.0D) {
/*  85 */             EnumFacing facing = BlockStateUtils.getFacing(func_145832_p()).func_176734_d();
/*  86 */             triggerNote(this.field_145850_b, this.field_174879_c, true);
/*  87 */             TileEntity tileentity = this.field_145850_b.func_175625_s(this.field_174879_c);
/*  88 */             IBlockState state = this.field_145850_b.func_180495_p(this.field_174879_c);
/*  89 */             if (func_145838_q() instanceof thaumcraft.common.blocks.devices.BlockArcaneEarToggle) {
/*  90 */               this.field_145850_b.func_180501_a(this.field_174879_c, state.func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(!BlockStateUtils.isEnabled(state))), 3);
/*     */             } else {
/*  92 */               this.redstoneSignal = 10;
/*  93 */               this.field_145850_b.func_180501_a(this.field_174879_c, state.func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(true)), 3);
/*     */             } 
/*     */             
/*  96 */             if (tileentity != null) {
/*     */               
/*  98 */               tileentity.func_145829_t();
/*  99 */               this.field_145850_b.func_175690_a(this.field_174879_c, tileentity);
/*     */             } 
/* 101 */             this.field_145850_b.func_175685_c(this.field_174879_c, func_145838_q(), true);
/* 102 */             this.field_145850_b.func_175685_c(this.field_174879_c.func_177972_a(facing), func_145838_q(), true);
/* 103 */             IBlockState state2 = this.field_145850_b.func_180495_p(this.field_174879_c);
/* 104 */             this.field_145850_b.markAndNotifyBlock(this.field_174879_c, this.field_145850_b.func_175726_f(this.field_174879_c), state2, state2, 3);
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 113 */   public static WeakHashMap<Integer, ArrayList<Integer[]>> noteBlockEvents = new WeakHashMap();
/*     */   
/*     */   public void updateTone() {
/*     */     try {
/* 117 */       EnumFacing facing = BlockStateUtils.getFacing(func_145832_p()).func_176734_d();
/* 118 */       IBlockState iblockstate = this.field_145850_b.func_180495_p(this.field_174879_c.func_177972_a(facing));
/* 119 */       Material material = iblockstate.func_185904_a();
/* 120 */       this.tone = 0;
/* 121 */       if (material == Material.field_151576_e)
/*     */       {
/* 123 */         this.tone = 1;
/*     */       }
/*     */       
/* 126 */       if (material == Material.field_151595_p)
/*     */       {
/* 128 */         this.tone = 2;
/*     */       }
/*     */       
/* 131 */       if (material == Material.field_151592_s)
/*     */       {
/* 133 */         this.tone = 3;
/*     */       }
/*     */       
/* 136 */       if (material == Material.field_151575_d)
/*     */       {
/* 138 */         this.tone = 4;
/*     */       }
/*     */       
/* 141 */       Block block = iblockstate.func_177230_c();
/*     */       
/* 143 */       if (block == Blocks.field_150435_aG)
/*     */       {
/* 145 */         this.tone = 5;
/*     */       }
/*     */       
/* 148 */       if (block == Blocks.field_150340_R)
/*     */       {
/* 150 */         this.tone = 6;
/*     */       }
/*     */       
/* 153 */       if (block == Blocks.field_150325_L)
/*     */       {
/* 155 */         this.tone = 7;
/*     */       }
/*     */       
/* 158 */       if (block == Blocks.field_150403_cj)
/*     */       {
/* 160 */         this.tone = 8;
/*     */       }
/*     */       
/* 163 */       if (block == Blocks.field_189880_di)
/*     */       {
/* 165 */         this.tone = 9;
/*     */       }
/*     */       
/* 168 */       func_70296_d();
/* 169 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void changePitch() {
/* 178 */     this.note = (byte)((this.note + 1) % 25);
/* 179 */     func_70296_d();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void triggerNote(World world, BlockPos pos, boolean sound) {
/* 187 */     byte i = -1;
/* 188 */     if (sound) {
/* 189 */       EnumFacing facing = BlockStateUtils.getFacing(func_145832_p()).func_176734_d();
/* 190 */       IBlockState iblockstate = world.func_180495_p(pos.func_177972_a(facing));
/* 191 */       Material material = iblockstate.func_185904_a();
/* 192 */       i = 0;
/* 193 */       if (material == Material.field_151576_e)
/*     */       {
/* 195 */         i = 1;
/*     */       }
/*     */       
/* 198 */       if (material == Material.field_151595_p)
/*     */       {
/* 200 */         i = 2;
/*     */       }
/*     */       
/* 203 */       if (material == Material.field_151592_s)
/*     */       {
/* 205 */         i = 3;
/*     */       }
/*     */       
/* 208 */       if (material == Material.field_151575_d)
/*     */       {
/* 210 */         i = 4;
/*     */       }
/*     */       
/* 213 */       Block block = iblockstate.func_177230_c();
/*     */       
/* 215 */       if (block == Blocks.field_150435_aG)
/*     */       {
/* 217 */         i = 5;
/*     */       }
/*     */       
/* 220 */       if (block == Blocks.field_150340_R)
/*     */       {
/* 222 */         i = 6;
/*     */       }
/*     */       
/* 225 */       if (block == Blocks.field_150325_L)
/*     */       {
/* 227 */         i = 7;
/*     */       }
/*     */       
/* 230 */       if (block == Blocks.field_150403_cj)
/*     */       {
/* 232 */         i = 8;
/*     */       }
/*     */       
/* 235 */       if (block == Blocks.field_189880_di)
/*     */       {
/* 237 */         i = 9;
/*     */       }
/*     */     } 
/* 240 */     world.func_175641_c(pos, func_145838_q(), i, this.note);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\devices\TileArcaneEar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */