**Dia:** 15/09/2023 
**Matéria:** Fundamentos de sistemas operacionais
**Assunto:** Namespaces
**Tags:** #linux

1. 7 namespaces https://www.redhat.com/sysadmin/7-linux-namespaces
2. User namespace https://www.redhat.com/sysadmin/building-container-namespaces
## Linux Capabilities
file permissions through implementation of user
Capability is the manner in wich capabilities can be assigned to threads

Each namespace have its own set of capabilities
Determine what each process in namespace can perfmorm

## User namespace
user identification number (UID), are one of the layers of security

É uma maneira de executar processos com permissões diferentes.
Um usuário tem permissão de criar um user namespace
Permissões são hierárquicas, e tem as mesmas do namespace pai

*É por isso que ao criar um container, criamos/especificamos um usuário (namespace)*

Any permissions granted in a user namespace only apply in its own namespace and possibly namespaces below it.


