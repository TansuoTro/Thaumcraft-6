/*     */ package thaumcraft.common.lib.network.misc;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraftforge.fml.common.network.ByteBufUtils;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*     */ import thaumcraft.Thaumcraft;
/*     */ import thaumcraft.api.golems.seals.ISeal;
/*     */ import thaumcraft.api.golems.seals.ISealConfigFilter;
/*     */ import thaumcraft.api.golems.seals.ISealConfigToggles;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.seals.SealPos;
/*     */ import thaumcraft.common.golems.seals.SealEntity;
/*     */ import thaumcraft.common.golems.seals.SealHandler;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ 
/*     */ public class PacketSealToClient
/*     */   extends Object
/*     */   implements IMessage, IMessageHandler<PacketSealToClient, IMessage>
/*     */ {
/*     */   BlockPos pos;
/*     */   EnumFacing face;
/*     */   String type;
/*     */   long area;
/*  31 */   boolean[] props = null;
/*     */   
/*     */   boolean blacklist;
/*     */   
/*     */   byte filtersize;
/*     */   NonNullList<ItemStack> filter;
/*     */   NonNullList<Integer> filterStackSize;
/*     */   byte priority;
/*     */   byte color;
/*     */   boolean locked;
/*     */   boolean redstone;
/*     */   String owner;
/*     */   
/*     */   public PacketSealToClient(ISealEntity se) {
/*  45 */     this.pos = (se.getSealPos()).pos;
/*  46 */     this.face = (se.getSealPos()).face;
/*  47 */     this.type = (se.getSeal() == null) ? "REMOVE" : se.getSeal().getKey();
/*  48 */     if (se.getSeal() != null && se.getSeal() instanceof thaumcraft.api.golems.seals.ISealConfigArea) {
/*  49 */       this.area = se.getArea().func_177986_g();
/*     */     }
/*  51 */     if (se.getSeal() != null && se.getSeal() instanceof ISealConfigToggles) {
/*  52 */       ISealConfigToggles cp = (ISealConfigToggles)se.getSeal();
/*  53 */       this.props = new boolean[cp.getToggles().length];
/*  54 */       for (int a = 0; a < cp.getToggles().length; a++) {
/*  55 */         this.props[a] = cp.getToggles()[a].getValue();
/*     */       }
/*     */     } 
/*  58 */     if (se.getSeal() != null && se.getSeal() instanceof ISealConfigFilter) {
/*  59 */       ISealConfigFilter cp = (ISealConfigFilter)se.getSeal();
/*  60 */       this.blacklist = cp.isBlacklist();
/*  61 */       this.filtersize = (byte)cp.getFilterSize();
/*  62 */       this.filter = cp.getInv();
/*  63 */       this.filterStackSize = cp.getSizes();
/*     */     } 
/*  65 */     this.priority = se.getPriority();
/*  66 */     this.color = se.getColor();
/*  67 */     this.locked = se.isLocked();
/*  68 */     this.redstone = se.isRedstoneSensitive();
/*  69 */     this.owner = se.getOwner();
/*     */   }
/*     */ 
/*     */   
/*     */   public void toBytes(ByteBuf dos) {
/*  74 */     dos.writeLong(this.pos.func_177986_g());
/*  75 */     dos.writeByte(this.face.ordinal());
/*  76 */     dos.writeByte(this.priority);
/*  77 */     dos.writeByte(this.color);
/*  78 */     dos.writeBoolean(this.locked);
/*  79 */     dos.writeBoolean(this.redstone);
/*  80 */     ByteBufUtils.writeUTF8String(dos, this.owner);
/*  81 */     ByteBufUtils.writeUTF8String(dos, this.type);
/*  82 */     dos.writeBoolean(this.blacklist);
/*  83 */     dos.writeByte(this.filtersize);
/*  84 */     for (int a = 0; a < this.filtersize; a++) {
/*  85 */       Utils.writeItemStackToBuffer(dos, (ItemStack)this.filter.get(a));
/*  86 */       dos.writeShort(((Integer)this.filterStackSize.get(a)).intValue());
/*     */     } 
/*  88 */     if (this.area != 0L) dos.writeLong(this.area); 
/*  89 */     if (this.props != null)
/*  90 */       for (boolean b : this.props) dos.writeBoolean(b);
/*     */        
/*     */   }
/*     */   
/*     */   public void fromBytes(ByteBuf dat) {
/*  95 */     this.pos = BlockPos.func_177969_a(dat.readLong());
/*  96 */     this.face = EnumFacing.field_82609_l[dat.readByte()];
/*  97 */     this.priority = dat.readByte();
/*  98 */     this.color = dat.readByte();
/*  99 */     this.locked = dat.readBoolean();
/* 100 */     this.redstone = dat.readBoolean();
/* 101 */     this.owner = ByteBufUtils.readUTF8String(dat);
/* 102 */     this.type = ByteBufUtils.readUTF8String(dat);
/* 103 */     this.blacklist = dat.readBoolean();
/* 104 */     this.filtersize = dat.readByte();
/* 105 */     this.filter = NonNullList.func_191197_a(this.filtersize, ItemStack.field_190927_a);
/* 106 */     this.filterStackSize = NonNullList.func_191197_a(this.filtersize, Integer.valueOf(0));
/* 107 */     for (a = 0; a < this.filtersize; a++) {
/* 108 */       this.filter.set(a, Utils.readItemStackFromBuffer(dat));
/* 109 */       this.filterStackSize.set(a, Integer.valueOf(dat.readShort()));
/*     */     } 
/* 111 */     if (!this.type.equals("REMOVE") && SealHandler.getSeal(this.type) != null) {
/* 112 */       if (SealHandler.getSeal(this.type) instanceof thaumcraft.api.golems.seals.ISealConfigArea)
/* 113 */         try { this.area = dat.readLong(); } catch (Exception a)
/*     */         { Exception exception; }
/* 115 */           if (SealHandler.getSeal(this.type) instanceof ISealConfigToggles)
/*     */         try {
/* 117 */           ISealConfigToggles cp = (ISealConfigToggles)SealHandler.getSeal(this.type);
/* 118 */           this.props = new boolean[cp.getToggles().length];
/* 119 */           for (int a = 0; a < cp.getToggles().length; a++) {
/* 120 */             this.props[a] = dat.readBoolean();
/*     */           }
/* 122 */         } catch (Exception a) {
/*     */           Exception exception;
/*     */         }  
/*     */     } 
/*     */   }
/*     */   
/*     */   public IMessage onMessage(PacketSealToClient message, MessageContext ctx) {
/* 129 */     if (message.type.equals("REMOVE")) {
/* 130 */       SealHandler.removeSealEntity(Thaumcraft.proxy.getClientWorld(), new SealPos(message.pos, message.face), true);
/*     */     } else {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 136 */         SealEntity seal = new SealEntity(Thaumcraft.proxy.getClientWorld(), new SealPos(message.pos, message.face), (ISeal)SealHandler.getSeal(message.type).getClass().newInstance());
/* 137 */         if (message.area != 0L) seal.setArea(BlockPos.func_177969_a(message.area)); 
/* 138 */         if (message.props != null && seal.getSeal() instanceof ISealConfigToggles) {
/* 139 */           ISealConfigToggles cp = (ISealConfigToggles)seal.getSeal();
/* 140 */           for (int a = 0; a < message.props.length; a++) {
/* 141 */             cp.setToggle(a, message.props[a]);
/*     */           }
/*     */         } 
/* 144 */         if (seal.getSeal() instanceof ISealConfigFilter) {
/* 145 */           ISealConfigFilter cp = (ISealConfigFilter)seal.getSeal();
/* 146 */           cp.setBlacklist(message.blacklist);
/* 147 */           for (int a = 0; a < message.filtersize; a++) {
/* 148 */             cp.setFilterSlot(a, (ItemStack)message.filter.get(a));
/* 149 */             cp.setFilterSlotSize(a, ((Integer)message.filterStackSize.get(a)).intValue());
/*     */           } 
/*     */         } 
/* 152 */         seal.setPriority(message.priority);
/* 153 */         seal.setColor(message.color);
/* 154 */         seal.setLocked(message.locked);
/* 155 */         seal.setRedstoneSensitive(message.redstone);
/* 156 */         seal.setOwner(message.owner);
/* 157 */         SealHandler.addSealEntity(Thaumcraft.proxy.getClientWorld(), seal);
/* 158 */       } catch (Exception e) {
/* 159 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */     
/* 163 */     return null;
/*     */   }
/*     */   
/*     */   public PacketSealToClient() {}
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\misc\PacketSealToClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */