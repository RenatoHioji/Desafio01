PGDMP         4                {         	   Desafio01    15.3    15.3     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16843 	   Desafio01    DATABASE     �   CREATE DATABASE "Desafio01" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';
    DROP DATABASE "Desafio01";
                postgres    false            �            1259    16858    produtos    TABLE     z   CREATE TABLE public.produtos (
    id integer,
    nome character varying,
    valor numeric(6,2),
    estoque integer
);
    DROP TABLE public.produtos;
       public         heap    postgres    false            �            1259    16871    vendas    TABLE     f   CREATE TABLE public.vendas (
    id_produto integer,
    quantidade integer,
    id_vendas integer
);
    DROP TABLE public.vendas;
       public         heap    postgres    false            �          0    16858    produtos 
   TABLE DATA           <   COPY public.produtos (id, nome, valor, estoque) FROM stdin;
    public          postgres    false    214           �          0    16871    vendas 
   TABLE DATA           C   COPY public.vendas (id_produto, quantidade, id_vendas) FROM stdin;
    public          postgres    false    215   �       �   �   x�α�0F���)2�uv�$.!]�(i��`��@��@�Y�$�>�it�yz8�43l�S)ݠ�[�e*ѧ�%D]ٹ5�*���#0+f�I[���VY��`����'):��#��}��`�sz�v�n���aj��p�	��J8&�N�+�<��r��I3�O���d�+"�H�2�      �      x�3�4�4����� �Y     