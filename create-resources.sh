#!/bin/bash

# Variáveis
RG="rg-vmubuntu"
LOCATION="brazilsouth"
VNET="nnet-Linux"
SUBNET="subnet-linux"
NSG="nsgsr-linux"
PUBLIC_IP="pip-ubuntu"
VM_NAME="vm-ubuntu"
USERNAME="eduardo"
PASSWORD="eduardo@2004"

# Criar Resource Group
az group create --name $RG --location $LOCATION

# Criar VNet e Subnet
az network vnet create \
  --resource-group $RG \
  --name $VNET \
  --subnet-name $SUBNET

# Criar NSG
az network nsg create \
  --resource-group $RG \
  --name $NSG

# Criar IP público
az network public-ip create \
  --resource-group $RG \
  --name $PUBLIC_IP

# Criar VM
az vm create \
  --resource-group $RG \
  --name $VM_NAME \
  --image Canonical:UbuntuServer:18.04-LTS:18.04.202401161 \
  --size Standard_B2s \
  --vnet-name $VNET \
  --subnet $SUBNET \
  --nsg $NSG \
  --public-ip-address $PUBLIC_IP \
  --authentication-type password \
  --admin-username $USERNAME \
  --admin-password "$PASSWORD"
